package com.lop.devtools.monstera.addon.entity.resource.renderparts

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.hashLayerName
import com.lop.devtools.monstera.addon.entity.hashLayerNameByIds
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers
import java.io.File

interface RenderPart {
    val partName: String
    val query : String
    val unsafeParent: Entity
    val unsafeRawEntity: ResEntity
    val unsafeRenderController: ResRenderControllers
    var material: String

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
    fun textureLayer(texturePath: String, layerName: String = "default")

    /**
     * add a single texture to the entity,
     * should only be called once
     *
     * @param texture a texture file that gets copied into the project
     * @param layerName used for multiple textures within the render controller, use textures() if you don't know what this is doing
     * @return the path that can be used in other entities when calling texture(<TexturePath>, <State>)
     */
    fun textureLayer(texture: File, layerName: String = "default"): String

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
    )
    /**
     * Add multiple textures and select them with a query
     *
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(
        textures: ArrayList<File>,
        query: Unit.() -> Molang,
        layerName: String = hashLayerName(textures, query(Unit).data)
    )

    /**
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(
        textures: ArrayList<File>,
        query: String,
        layerName: String = hashLayerName(textures, query)
    )

    /**
     * add a new geometry as a new "layer"
     * @param geoId the geometry identifier like: geometry.pig
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(geoId: String, layerName: String = "default")

    /**
     * add a new geometry as a new "layer"
     *
     * @param file the geometry file
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(file: File, layerName: String = "default")

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(
        files: ArrayList<File>,
        query: Unit.() -> Molang,
        layerName: String = hashLayerName(files, query(Unit).data),

        )
    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(
        files: ArrayList<File>,
        query: Molang,
        layerName: String = hashLayerName(files, query.data)
    )

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries by geometry ids (this only works with geometries that are already in the pack or are vanilla)
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(
        geoIds: ArrayList<String>,
        query: Molang,
        layerName: String = hashLayerNameByIds(geoIds, query.data)
    )

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(
        files: ArrayList<File>,
        query: String,
        layerName: String = hashLayerName(files, query)
    )

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries by geometry ids (this only works with geometries that are already in the pack or are vanilla)
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(
        geoIds: ArrayList<String>,
        query: String,
        layerName: String = hashLayerNameByIds(geoIds, query)
    )

    fun build()
}