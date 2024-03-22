@file:Suppress("MemberVisibilityCanBePrivate")

package com.lop.devtools.monstera.addon.entity.resource

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.extractAnimationIdsFromFile
import com.lop.devtools.monstera.addon.entity.hashLayerName
import com.lop.devtools.monstera.addon.entity.hashLayerNameByIds
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.animcontroller.AnimationControllers
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.lang.langKey
import com.lop.devtools.monstera.files.res.entities.ResEntity
import com.lop.devtools.monstera.files.res.materials.Materials
import com.lop.devtools.monstera.files.res.rendercontrollers.ResRenderControllers
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File

open class ResourceEntity(val unsafeParent: Entity) {
    val unsafeRawEntity: ResEntity = ResEntity()
    val unsafeRenderController: ResRenderControllers = ResRenderControllers()
    val unsafeControllers: AnimationControllers = AnimationControllers()
    val unsafeComponents: ResourceEntityComponents =
        ResourceEntityComponents(unsafeParent, unsafeRawEntity, unsafeRenderController)
    val unsafeMaterials: Materials = Materials.instance(unsafeParent.addon)
    var unsafeApplyDefaultTexture: Boolean = true
    var unsafeApplyDefaultGeometry: Boolean = true

    var disableRender: Boolean = false
    val renderParts: ArrayList<RenderPart> = ArrayList()
    var hasDefaultTexture = false
    private fun logger() = getMonsteraLogger("Resource")


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
        hasDefaultTexture = true
        renderPart("default") {
            textureLayer(texturePath, layerName)
        }
        unsafeApplyDefaultTexture = false
    }

    /**
     * add a single texture to the entity,
     * should only be called once
     *
     * @param texture a texture file that gets copied into the project
     * @param layerName used for multiple textures within the render controller, use textures() if you don't know what this is doing
     * @return the path that can be used in other entities when calling texture(<TexturePath>, <State>)
     */
    fun textureLayer(texture: File, layerName: String = "default") {
        hasDefaultTexture = true
        renderPart("default") {
            textureLayer(texture, layerName)
        }
        unsafeApplyDefaultTexture = false
    }

    /**
     * Add multiple textures and select them with a query
     *
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(textures: ArrayList<File>, query: Molang, layerName: String= hashLayerName(textures, query.data)) {
        renderPart("default") {
            textureLayer(textures, query, layerName)
        }
        unsafeApplyDefaultTexture = false
    }

    /**
     * Add multiple textures and select them with a query
     *
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(textures: ArrayList<File>, query: Unit.() -> Molang, layerName: String= hashLayerName(textures, query(Unit).data)) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(textures, query, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    /**
     * @param textures the texture files to add to a layer
     * @param query the query to select the texture like, "Query.variant"
     * @param layerName the identifier for the layer, leave empty to auto generate a name
     */
    fun textureLayer(textures: ArrayList<File>, query: String, layerName: String= hashLayerName(textures, query)) {
        unsafeRawEntity.apply {
            description {
                renderPart("default") {
                    textureLayer(textures, query, layerName)
                }
            }
        }
        unsafeApplyDefaultTexture = false
    }

    /**
     * add a new geometry as a new "layer"
     * @param geoId the geometry identifier like: geometry.pig
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(geoId: String, layerName: String = "default") {
        renderPart("default") {
            geometryLayer(geoId, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * add a new geometry as a new "layer"
     *
     * @param file the geometry file
     * @param layerName the name of the layer to prevent overwriting
     */
    fun geometryLayer(file: File, layerName: String= "default") {
        renderPart("default") {
            geometryLayer(file, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(files: ArrayList<File>, query: Molang, layerName: String= hashLayerName(files, query.data)) {
        renderPart("default") {
            geometryLayer(files, query, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(files: ArrayList<File>, query: Unit.() -> Molang, layerName: String= hashLayerName(files, query(Unit).data)) {
        renderPart("default") {
            geometryLayer(files, query, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(geoIds: ArrayList<String>, query: Molang, layerName: String= hashLayerNameByIds(geoIds, query.data)) {
        renderPart("default") {
            geometryLayerByIds(geoIds, query, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param geoIds the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayerByIds(geoIds: ArrayList<String>, query: () -> Molang, layerName: String= hashLayerNameByIds(geoIds, query().data)) {
        geometryLayerByIds(geoIds, query(), layerName)
    }

    /**
     * add multiple geometries to a layer and cycle through them with a molang query
     * @param files the geometries
     * @param query the query to select the geometry like "Query.variant"
     * @param layerName optional to identify the layer in the render-controller
     */
    fun geometryLayer(files: ArrayList<File>, query: String, layerName: String= hashLayerName(files, query)) {
        renderPart("default") {
            geometryLayer(files, query, layerName)
        }
        unsafeApplyDefaultGeometry = false
    }

    /**
     * @param name the name of the animation, can be used in the animation controller
     * @param id the animation identifier like "animation.pig.walk"
     */
    fun animation(name: String, id: String) {
        unsafeRawEntity.apply {
            description {
                animation(name, id)
            }
        }
    }

    /**
     * @param file the animations
     * @param animName set the name for all animations (input is the identifier), default is the string after the last dot "animation.entity.walk" => "walk"
     */
    fun animation(file: File, animName: (String) -> String  = { it.split(".").last() }) {
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
                    animation(animName(it), it)
                }
            }
        }
    }

    /**
     * add a animation controller
     *
     * @param name the name of the controller
     */
    fun animationController(name: String, query: Molang = Query.True, data: AnimationControllers.Controller.() -> Unit) {
        val idName = "controller.animation.${unsafeParent.name}.${name.removePrefix("controller.animation.")}"
        unsafeControllers.animController(idName, data)

        unsafeRawEntity.apply {
            description {
                animController(name, idName, query)
            }
        }
    }

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
    fun components(data: ResourceEntityComponents.() -> Unit) {
        unsafeComponents.apply(data)
    }

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
    fun renderPart(partName: String, query: Molang, data: RenderPart.() -> Unit) {
        val part = renderParts.firstOrNull { it.partName == partName }?.apply(data) // eventually add a warning
        if (part == null) {
            val renderPart = RenderPart(partName, query, unsafeParent).apply(data)
            renderParts.add(renderPart)
        }
    }

    fun defaultRenderPart(data: RenderPart.() -> Unit) {
        renderPart("default", Query.True, data)
    }

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
    fun renderPart(partName: String, data: RenderPart.() -> Unit) {
        val query = Query.True
        val part = renderParts.firstOrNull { it.partName == partName }?.apply(data) // eventually add a warning
        if (part == null) {
            val renderPart = RenderPart(partName, query, unsafeParent).apply(data)
            renderParts.add(renderPart)
        }
    }

    fun build() {
        unsafeComponents.build()

        unsafeRawEntity.description {
            identifier = unsafeParent.getIdentifier()
            langKey("entity.$identifier.name", unsafeParent.displayName)
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
            unsafeRenderController.build(unsafeParent.name, unsafeParent.addon.config.paths.resRenderControllers)
        }

        unsafeRawEntity.build(unsafeParent.name, unsafeParent.addon.config.paths.resEntity)

        //controllers check themselves if they should be build
        unsafeControllers.build(
            unsafeParent.name,
            unsafeParent.addon.config.paths.resAnimController
        )
    }
}

