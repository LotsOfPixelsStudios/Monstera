package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.extractAnimationIdsFromFile
import com.lop.devtools.monstera.addon.entity.resource.renderparts.BasicRenderPart
import com.lop.devtools.monstera.addon.entity.resource.renderparts.RenderPart
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.animcontroller.AnimController
import com.lop.devtools.monstera.files.animcontroller.AnimStateComponent
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File

abstract class ResourceEntityImpl(override val unsafeParent: Entity) : ResourceEntity {
    override var disableRender: Boolean = false
    val renderParts: ArrayList<BasicRenderPart> = ArrayList()
    override var hasDefaultTexture = false
    private fun logger() = getMonsteraLogger("Resource")


    override fun textureLayer(texturePath: String, layerName: String) {
        hasDefaultTexture = true
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(texturePath, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    override fun textureLayer(texture: File, layerName: String) {
        hasDefaultTexture = true
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(texture, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    override fun textureLayer(textures: ArrayList<File>, query: Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(textures, query, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    override fun textureLayer(textures: ArrayList<File>, query: Unit.() -> Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(textures, query, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    override fun textureLayer(textures: ArrayList<File>, query: String, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(textures, query, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    override fun geometryLayer(geoId: String, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayer(geoId, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayer(file: File, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayer(file, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayer(files: ArrayList<File>, query: Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayer(files, query, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayer(files: ArrayList<File>, query: Unit.() -> Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayer(files, query, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayerByIds(geoIds: ArrayList<String>, query: Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayerByIds(geoIds, query, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayerByIds(geoIds: ArrayList<String>, query: Unit.() -> Molang, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayerByIds(geoIds, query, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun geometryLayer(files: ArrayList<File>, query: String, layerName: String) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    geometryLayer(files, query, layerName)
                }
            }
        }
        unsafeApplyDefaultGeometry = false
    }

    override fun animation(name: String, id: String) {
        unsafeRawEntity.apply {
            description {
                animations {
                    animation(name, id)
                }
            }
        }
    }

    override fun animation(file: File) {
        val animations = extractAnimationIdsFromFile(file)
        if (animations.isEmpty()) {
            logger().warn("No animations found in file: ${file.name}")
            return
        }
        val uniqueFilename = getUniqueFileName(file)
        val target = unsafeParent.addon.config.paths.resAnim.resolve(uniqueFilename)
        file.copyTo(
            target.toFile(),
            overwrite = true
        )

        animations.forEach {
            unsafeRawEntity.apply {
                description {
                    animations {
                        animation(it.split(".").last(), it)
                    }
                }
            }
        }
    }

    override fun animationController(name: String, query: Molang, data: AnimController.() -> Unit) {
        val idName = "controller.animation.${unsafeParent.name}.${name.removePrefix("controller.animation.")}"
        unsafeControllers.animController(idName, data)

        unsafeRawEntity.apply {
            description {
                animations {
                    animation(name, idName)
                }
            }
        }
        unsafeRawEntity.apply {
            description {
                scripts {
                    animate(arrayListOf(name))
                }
            }
        }
    }

    override fun components(data: ResourceEntityComponents.() -> Unit) {
        unsafeComponents.apply(data)
    }

    override fun renderPart(partName: String, query: Molang, data: RenderPart.() -> Unit) {
        val part = renderParts.firstOrNull { it.partName == partName }?.apply(data) // eventually add a warning
        if (part == null) {
            val renderPart = BasicRenderPart(partName, query, unsafeParent).apply(data)
            renderParts.add(renderPart)
        }
    }

    override fun renderPart(partName: String, data: RenderPart.() -> Unit) {
        val query = Query.True
        val part = renderParts.firstOrNull { it.partName == partName }?.apply(data) // eventually add a warning
        if (part == null) {
            val renderPart = BasicRenderPart(partName, query, unsafeParent).apply(data)
            renderParts.add(renderPart)
        }
    }

    override fun AnimStateComponent.controller(name: String, query: Query, data: AnimController.() -> Unit) {
        controller("${unsafeParent.name}.$name", query)
        animationController(name, Query.False, data)
    }

    override fun AnimStateComponent.controller(name: String, query: () -> Query, data: AnimController.() -> Unit) {
        controller("${unsafeParent.name}.$name", query)
        animationController(name, Query.False, data)
    }

    override fun build() {
        unsafeComponents.build()

        unsafeRawEntity.description {
            identifier(unsafeParent.getIdentifier(), unsafeParent.displayName)
        }
        if (!disableRender) {
            if (unsafeApplyDefaultTexture) {
                unsafeRawEntity.apply {
                    description {
                        renderPart("default") {
                            textureLayer(unsafeParent.addon.config.defaultResource.defaultTexturePath)
                        }
                    }
                }
            }

            if (unsafeApplyDefaultGeometry)
                unsafeRawEntity.apply {
                    description {
                        renderPart("default") {
                            geometryLayer(unsafeParent.addon.config.defaultResource.defaultGeo)
                        }
                    }
                }
            renderParts.forEach { it.build() }
        }


        if (!disableRender) {
            unsafeRenderController.unsafe.build(unsafeParent.name, unsafeParent.addon.config.paths.resRenderControllers)
        }

        unsafeRawEntity.unsafe.build(unsafeParent.name, unsafeParent.addon.config.paths.resEntity)

        //controllers check themselves if they should be build
        unsafeControllers.unsafe.build(
            unsafeParent.name,
            unsafeParent.addon.config.paths.resAnimController
        )
    }
}

