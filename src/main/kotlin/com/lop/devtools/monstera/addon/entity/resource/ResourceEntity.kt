package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.behaviour.AnimationControllerExtensions
import com.lop.devtools.monstera.addon.entity.hashLayerName
import com.lop.devtools.monstera.addon.entity.hashLayerNameByIds
import com.lop.devtools.monstera.addon.entity.resource.renderparts.RenderPart
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.animcontroller.AnimController
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.materials.MaterialExtensions
import com.lop.devtools.monstera.files.res.materials.Materials
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers
import java.io.File

interface ResourceEntity: MaterialExtensions, AnimationControllerExtensions {
    val unsafeParent: Entity
    val unsafeRawEntity: ResEntity
    val unsafeAnimations: ArrayList<Pair<String, String>>
    val unsafeRenderController: ResRenderControllers
    val unsafeControllers: AnimationControllers
    val unsafeComponents: ResourceEntityComponents
    val unsafeMaterials: Materials

    var unsafeApplyDefaultTexture: Boolean
    var unsafeApplyDefaultGeometry: Boolean

    var disableRender: Boolean
    var hasDefaultTexture : Boolean

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
    fun textureLayer(texture: File, layerName: String = "default")

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
        query:Unit.() -> Molang,
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
        query: Molang,
        layerName: String = hashLayerName(files, query.data)
    )
    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(
        files: ArrayList<File>,
        query: Unit.() -> Molang,
        layerName: String = hashLayerName(files, query(Unit).data)
    )

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries
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
     * @param geoIds the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(
        geoIds: ArrayList<String>,
        query: Unit.() -> Molang,
        layerName: String = hashLayerNameByIds(geoIds, query(Unit).data)
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
     * @param name the name of the animation, can be used in the animation controller
     * @param id the animation identifier like "animation.pig.walk"
     */
    fun animation(name: String, id: String)

    /**
     * animation names to use in an anim controller are the last index when splitting the id on a dot ->
     * "animation.entity.walk" => "walk" can be used
     *
     * @param file the animations
     */
    fun animation(file: File)

    /**
     * add a animation controller
     *
     * @param name the name of the controller
     */
    fun animationController(name: String, query: Molang = Query.True, data: AnimController.() -> Unit)

    /**
     * add stuff like spawnEgg, attachable etc
     *
     * ```
     * enableAttachment = false
     * material = "parrot"
     * spawnEgg { }
     * particleEffects { }
     * soundEffects { }
     * locators { }
     * scripts { }
     * ```
     */
    fun components(data: ResourceEntityComponents.() -> Unit)

    /**
     * add a geometry and a texture with a separate render controller,
     * so you can control if that part is rendered or not by Molang Queries.
     *
     * ```
     * renderPart("partName", !Query.isSheared and Query.isBaby ) {
     *     material = "parrot"
     *     textureLayer(getResource("default_texture.png"))
     *     geometryLayer(getResource("default_model.geo.json"))
     * }
     * ```
     */
    fun renderPart(partName: String, query: Molang ,data: RenderPart.() -> Unit)

    /**
     * add a geometry and a texture with a separate render controller.
     * This part will always be rendered as it defaults the query to Query.True.
     *
     * ```
     * renderPart("partName") {
     *     material = "parrot"
     *     textureLayer(getResource("default_texture.png"))
     *     geometryLayer(getResource("default_model.geo.json"))
     * }
     * ```
     *
     * If you want to control the part with Molang queries, define a query
     * ```
     * renderPart("partName", !Query.isSheared and Query.isBaby) {
     *     material = "parrot"
     *     textureLayer(getResource("default_texture.png"))
     *     geometryLayer(getResource("default_model.geo.json"))
     * }
     * ```
     */
    fun renderPart(partName: String ,data: RenderPart.() -> Unit)

    /**
     * Internal function to build the resource entity, anims, etc
     */
    fun build()
}