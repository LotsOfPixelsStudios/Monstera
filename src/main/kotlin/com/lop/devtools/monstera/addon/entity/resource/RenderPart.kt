@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.getGeoId
import com.lop.devtools.monstera.addon.entity.hashLayerName
import com.lop.devtools.monstera.addon.entity.hashLayerNameByIds
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers
import java.awt.Color
import java.io.File

open class RenderPart(val partName: String, query: Molang, val unsafeParent: Entity) {
    val query: String = query.data
    val unsafeRenderController: ResRenderControllers = unsafeParent.unsafeResourceEntity.unsafeRenderController
    val unsafeRawEntity: ResEntity = unsafeParent.unsafeResourceEntity.unsafeRawEntity

    var material: String = "parrot"
    var hasTextureLayer = false

    private fun getRenderControllerId() = "${unsafeParent.name}.$partName"

    /**
     * CAUTION, path refers to the path within the build file, you probably want to give a File as a texture
     *
     * add a single texture to the entity,
     * should only be called once
     *
     * @param texturePath a texture path to the texture in the build files
     * @param layerName used for multiple textures within the render controller, use textures() if you don't know what this is doing
     * @return the path that can be used in other entities when calling texture(<TexturePath>, <State>)
     */
    fun textureLayer(texturePath: String, layerName: String = "default") {
        hasTextureLayer = true
        val id = "${partName}_$layerName"
        unsafeRawEntity.apply {
            description {
                this.texture(id, texturePath)
            }
        }
        unsafeRenderController.apply {
            controllers(getRenderControllerId()) {
                texture("Texture.$id")
            }
        }
    }

    /**
     * add a single texture to the entity,
     * should only be called once
     *
     * @param texture a texture file that gets copied into the project
     * @param layerName used for multiple textures within the render controller, use textures() if you don't know what this is doing
     * @return the path that can be used in other entities when calling texture(<TexturePath>, <State>)
     */
    fun textureLayer(texture: File, layerName: String = "default"): String {
        val uniqueFilename = getUniqueFileName(texture)
        val path = "textures/monstera/${uniqueFilename.removeSuffix(".png")}"
        val target = unsafeParent.addon.config.paths.resTextures.resolve("monstera").resolve(uniqueFilename)
        texture.copyTo(target.toFile(), true)
        textureLayer(path, layerName)
        return path
    }

    /**
     * Add multiple textures and select them with a query
     *
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(
        textures: ArrayList<File>,
        query: Molang,
        layerName: String = hashLayerName(textures, query.data)
    ) {
        textureLayer(textures, query.data, layerName)
    }

    /**
     * Add multiple textures and select them with a query
     *
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(
        textures: ArrayList<File>,
        query: () -> Molang,
        layerName: String = hashLayerName(textures, query().data)
    ) {
        textureLayer(textures, query().data, layerName)
    }

    /**
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(textures: ArrayList<File>, query: String, layerName: String = hashLayerName(textures, query)) {
        hasTextureLayer = true
        val id = "${partName}_$layerName"

        val noInvalidateLayerName = id.replace("-", "")
        textures.forEachIndexed { index, file ->
            unsafeRawEntity.apply {
                description {
                    texture("${noInvalidateLayerName}_v$index", file, unsafeParent.addon)
                }
            }
            unsafeRenderController.apply {
                controllers(getRenderControllerId()) {
                    arrays {
                        textures(noInvalidateLayerName, query) {
                            add("Texture.${noInvalidateLayerName}_v$index")
                        }
                    }
                }
            }
        }
    }

    /**
     * add a new geometry as a new "layer"
     * @param geoId the geometry identifier like: geometry.pig
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(geoId: String, layerName: String = "default") {
        val id = "${partName}_$layerName"
        unsafeRawEntity.apply {
            description {
                this.geometry(id, geoId)
            }
        }
        unsafeRenderController.apply {
            controllers(getRenderControllerId()) {
                geometry = "Geometry.$id"
            }
        }
    }

    /**
     * add a new geometry as a new "layer"
     *
     * @param file the geometry file
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(file: File, layerName: String = "default") {
        val uniqueFilename = getUniqueFileName(file)
        val id = getGeoId(file)
        val target = unsafeParent.addon.config.paths.resModels.resolve("entity").resolve(uniqueFilename)
        file.copyTo(target.toFile(), true)
        geometryLayer(id, layerName)
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(files: ArrayList<File>, query: Molang, layerName: String = hashLayerName(files, query.data)) {
        geometryLayer(files, query.data, layerName)
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(
        files: ArrayList<File>,
        query: () -> Molang,
        layerName: String = hashLayerName(files, query().data)
    ) {
        geometryLayer(files, query().data, layerName)
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries by geometry ids (this only works with geometries that are already in the pack or are vanilla)
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(geoIds: ArrayList<String>, query: Molang, layerName: String) {
        geometryLayerByIds(geoIds, query.data, layerName)
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(files: ArrayList<File>, query: String, layerName: String = hashLayerName(files, query)) {
        val id = "${partName}_$layerName"

        files.forEachIndexed { index, file ->
            val uniqueFilename = getUniqueFileName(file)
            val geoId = getGeoId(file)
            val target = unsafeParent.addon.config.paths.resModels.resolve("entity").resolve(uniqueFilename)
            file.copyTo(target.toFile(), true)

            unsafeRawEntity.apply {
                description {
                    geometry("${id}_v$index", geoId)
                }
            }
            unsafeRenderController.apply {
                controllers(getRenderControllerId()) {
                    arrays {
                        geometries(layerName, query) {
                            add("Geometry.${id}_v$index")
                        }
                    }
                }
            }
        }
    }

    fun onHurtColor(color: Color) {
        unsafeRenderController.controllers(getRenderControllerId()) {
            this.onHurtColor(color)
        }
    }

    fun onFireColor(color: Color) {
        unsafeRenderController.controllers(getRenderControllerId()) {
            this.onFireColor(color)
        }
    }

    fun overlayColor(color: Color) {
        unsafeRenderController.controllers(getRenderControllerId()) {
            this.overlayColor(color)
        }
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries by geometry ids (this only works with geometries that are already in the pack or are vanilla)
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(geoIds: ArrayList<String>, query: String, layerName: String= hashLayerNameByIds(geoIds, query)) {
        val id = "${partName}_$layerName"

        geoIds.forEachIndexed { index, geoId ->
            unsafeRawEntity.apply {
                description {
                    geometry("${id}_v$index", geoId)
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

    fun build() {
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
                material(partName, material)
                renderController("controller.render.${unsafeParent.name}.$partName", Query(query))
            }
        }
    }
}