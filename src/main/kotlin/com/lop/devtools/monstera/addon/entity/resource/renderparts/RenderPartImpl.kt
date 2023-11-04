package com.lop.devtools.monstera.addon.entity.resource.renderparts

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.getGeoId
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.getUniqueFileName
import java.io.File

abstract class RenderPartImpl(override val unsafeParent: Entity) : RenderPart {
    override var material: String = "parrot"
    var hasTextureLayer = false

    override fun textureLayer(texturePath: String, layerName: String) {
        hasTextureLayer = true
        val id = "${partName}_$layerName"
        unsafeRawEntity.apply {
            description {
                textures { unsafe.texture(id, texturePath) }
            }
        }
        unsafeRenderController.apply {
            controllers("${unsafeParent.name}.$partName") {
                texture("Texture.$id")
            }
        }
    }

    override fun textureLayer(texture: File, layerName: String): String {
        val uniqueFilename = getUniqueFileName(texture)
        val path = "textures/monstera/${uniqueFilename.removeSuffix(".png")}"
        val target = unsafeParent.addon.config.paths.resTextures.resolve("monstera").resolve(uniqueFilename)
        texture.copyTo(target.toFile(), true)
        textureLayer(path, layerName)
        return path
    }

    override fun textureLayer(textures: ArrayList<File>, query: Molang, layerName: String) {
        textureLayer(textures, query.data, layerName)
    }

    override fun textureLayer(textures: ArrayList<File>, query: Unit.() -> Molang, layerName: String) {
        textureLayer(textures, query(Unit).data, layerName)
    }

    override fun textureLayer(textures: ArrayList<File>, query: String, layerName: String) {
        hasTextureLayer = true
        val id = "${partName}_$layerName"

        val noInvalidateLayerName = id.replace("-", "")
        textures.forEachIndexed { index, file ->
            unsafeRawEntity.apply {
                description {
                    textures { texture("${noInvalidateLayerName}_v$index", file, unsafeParent.addon) }
                }
            }
            unsafeRenderController.apply {
                controllers("${unsafeParent.name}.$partName") {
                    arrays {
                        textures(noInvalidateLayerName, query) {
                            add("Texture.${noInvalidateLayerName}_v$index")
                        }
                    }
                }
            }
        }
    }

    override fun geometryLayer(geoId: String, layerName: String) {
        val id = "${partName}_$layerName"
        unsafeRawEntity.apply {
            description {
                geometries {
                    this.geometry(id, geoId)
                }
            }
        }
        unsafeRenderController.apply {
            controllers("${unsafeParent.name}.$partName") {
                geometry("Geometry.$id")
            }
        }
    }

    override fun geometryLayer(file: File, layerName: String) {
        val uniqueFilename = getUniqueFileName(file)
        val id = getGeoId(file)
        val target = unsafeParent.addon.config.paths.resModels.resolve("entity").resolve(uniqueFilename)
        file.copyTo(target.toFile(), true)
        geometryLayer(id, layerName)
    }

    override fun geometryLayer(files: ArrayList<File>, query: Molang, layerName: String) {
        geometryLayer(files, query.data, layerName)
    }

    override fun geometryLayer(files: ArrayList<File>, query: Unit.() -> Molang, layerName: String) {
        geometryLayer(files, query(Unit).data, layerName)
    }

    override fun geometryLayerByIds(geoIds: ArrayList<String>, query: Molang, layerName: String) {
        geometryLayerByIds(geoIds, query.data, layerName)
    }

    override fun geometryLayer(files: ArrayList<File>, query: String, layerName: String) {
        val id = "${partName}_$layerName"

        files.forEachIndexed { index, file ->
            val uniqueFilename = getUniqueFileName(file)
            val geoId = getGeoId(file)
            val target = unsafeParent.addon.config.paths.resModels.resolve("entity").resolve(uniqueFilename)
            file.copyTo(target.toFile(), true)
            unsafeRawEntity.apply {
                description {
                    geometries {
                        geometry("${id}_v$index", geoId)
                    }
                }
            }
            unsafeRenderController.apply {
                controllers("${unsafeParent.name}.$partName") {
                    arrays {
                        geometries(layerName, query) {
                            add("Geometry.${id}_v$index")
                        }
                    }
                }
            }
        }
    }

    override fun geometryLayerByIds(geoIds: ArrayList<String>, query: String, layerName: String) {
        val id = "${partName}_$layerName"

        geoIds.forEachIndexed { index, geoId ->
            unsafeRawEntity.apply {
                description {
                    geometries {
                        geometry("${id}_v$index", geoId)
                    }
                }
            }
            unsafeRenderController.apply {
                controllers("${unsafeParent.name}.$partName") {
                    arrays {
                        geometries(layerName, query) {
                            add("Geometry.${id}_v$index")
                        }
                    }
                }
            }
        }
    }

    override fun build() {
        unsafeRenderController.apply {
            controllers("${unsafeParent.name}.$partName") {
                materials("Material.$partName")
            }
        }
        val unsafeResEntity = unsafeParent.unsafeResourceEntity
        if (!hasTextureLayer && partName != "default") {
            if (!unsafeResEntity.hasDefaultTexture && !unsafeResEntity.unsafeApplyDefaultTexture || unsafeResEntity.disableRender) {
                throw IllegalArgumentException(
                    "No default texture! " +
                            "You either need to assign a default texture or add a part texture"
                )
            } else {
                unsafeRenderController.apply {
                    controllers("${unsafeParent.name}.$partName") {
                        texture("Texture.default")
                    }
                }
            }
        }
        unsafeRawEntity.apply {
            description {
                materials {
                    material(partName, material)
                }

                renderControllers {
                    renderCon("controller.render.${unsafeParent.name}.$partName") { Query(query) }
                }
            }
        }
    }
}