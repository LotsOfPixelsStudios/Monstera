@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.res.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.TextureIndex
import com.lop.devtools.monstera.files.res.entities.comp.*
import com.lop.devtools.monstera.files.res.materials.Material
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File
import java.lang.Error
import java.nio.file.Path

class ResEntity: MonsteraBuildableFile {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.resEntity ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for entity file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for entity file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.resEntity ?: "1.10.0",
                this
            )
        )
        return Result.success(target)
    }

    /**
     * load json entity with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.10.0",

        @SerializedName("minecraft:client_entity")
        @Expose
        var entity: ResEntity
    )

    @SerializedName("description")
    @Expose
    var description: Description? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * description {
     *     identifier = "namespace:my_entity"
     *     minEngineVersion = "1.8.0"
     *     enableAttachables = true
     *     material("default", Material.ENTITY)
     *     geometry("default", "my_ent.geo")
     *     texture("default", "textures/entity/bee/bee")
     *     renderController("controller.render.my_ent")
     *     animation("animation.my_ent.walk")
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        description = (description ?: Description()).apply(data)
    }

    class Description {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null

        /**
         * this may break things in the RP, don't touch unless you have to
         */
        @SerializedName("min_engine_version")
        @Expose
        var minEngineVersion: String? = null

        @SerializedName("enable_attachables")
        @Expose
        var enableAttachables: Boolean? = null

        @SerializedName("materials")
        @Expose
        var materialsData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * adds a material to the entity, can be called multiple times
         *
         * ```
         * material("default", Material.ENTITY)
         * ```
         *
         * @param refIdentifier the identifier that is referenced in the render controller
         * @param material the name of the material
         */
        fun material(refIdentifier: String = "default", material: Material) {
            material(refIdentifier, material.name)
        }

        /**
         * adds a material to the entity, can be called multiple times
         *
         * ```
         * material("default", "spider")
         * material("invisible", "spider_invisible")
         * ```
         *
         * @param refIdentifier the identifier that is referenced in the render controller
         * @param material the name of the material
         */
        @OptIn(MonsteraBuildSetter::class)
        fun material(refIdentifier: String = "default", material: String) {
            materialsData = (materialsData ?: mutableMapOf()).apply {
                put(refIdentifier, material)
            }
        }

        @SerializedName("geometry")
        @Expose
        var geometryData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * adds a geometry to the entity, can be called multiple times
         *
         * ```
         * geometry("default", "geometry.creeper")
         * geometry("charged", "geometry.creeper.charged")
         * ```
         *
         * @param refIdentifier the identifier that is referenced in the render controller
         * @param geoID the geometry id within the geo file
         */
        @OptIn(MonsteraBuildSetter::class)
        fun geometry(refIdentifier: String = "default", geoID: String) {
            geometryData = (geometryData ?: mutableMapOf()).apply {
                put(refIdentifier, geoID)
            }
        }

        @SerializedName("textures")
        @Expose
        var texturesData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun texture(refIdentifier: String = "default", path: String) {
            texturesData = (texturesData ?: mutableMapOf()).apply {
                put(refIdentifier, path)
            }
        }

        fun texture(state: String, file: File, addon: Addon) {
            val targetName = getUniqueFileName(file).removeSuffix(".png")
            val targetPath = addon.config.paths.resTextures.resolve("monstera").resolve("$targetName.png")
            TextureIndex.instance(addon).textures.add("textures/monstera/$targetName")
            file.copyTo(targetPath.toFile(), true)
            texture(state,"textures/monstera/$targetName")
        }

        @SerializedName("animations")
        @Expose
        var animationsData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * adds an animation
         *
         * @param refIdentifier the reference name for an animation controller
         * @param animID the animation id e.g. animation.husk.walk
         */
        @OptIn(MonsteraBuildSetter::class)
        fun animation(refIdentifier: String = "default", animID: String) {
            animationsData = (animationsData ?: mutableMapOf()).apply {
                put(refIdentifier, animID)
            }
        }

        /**
         * adds an animation controller in the classic sense, this function only calls animation() and scripts { }
         *
         * ```
         * animController("walk", "controller.animation.attack", Query.isBaby)
         * ```
         */
        fun animController(name: String, identifier: String, condition: Molang = Query.True) {
            animation(name, identifier)
            scripts {
                animate(name, condition)
            }
        }

        //@SerializedName("materials")
        //@Expose
        //var materialsData: ResEntityAnimationControllers? = null

        @SerializedName("render_controllers")
        @Expose
        var renderControllersData: MutableList<Any>? = null
            @MonsteraBuildSetter set

        /**
         * adds a render controller
         *
         * @param condition takes a query when the controller should be active
         */
        @OptIn(MonsteraBuildSetter::class)
        fun renderController(controller: String, condition: Molang = Query.True) {
            val sanName = "controller.render.${controller.removePrefix("controller.render.")}"

            renderControllersData = (renderControllersData ?: mutableListOf()).apply {
                if(condition == Query.True)
                    add(sanName)
                else
                    add(mutableMapOf(sanName to condition.data))
            }
        }

        @SerializedName("locators")
        @Expose
        var locatorsData: ResEntityLocators? = null
            @MonsteraBuildSetter set

        /**
         *
         */
        @OptIn(MonsteraBuildSetter::class)
        fun locators(data: ResEntityLocators.() -> Unit) {
            locatorsData = (locatorsData ?: ResEntityLocators().apply(data))
        }

        @SerializedName("spawn_egg")
        @Expose
        var spawnEggData: ResEntitySpawnEgg? = null
            @MonsteraBuildSetter set

        /**
         * creates a spawn egg for the entitiy
         *
         * ```
         * spawnEgg {
         *      eggByColor()
         *      eggByFile()
         * }
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun spawnEgg(data: ResEntitySpawnEgg.() -> Unit) {
            spawnEggData = (spawnEggData ?: ResEntitySpawnEgg()).apply(data)
        }

        @SerializedName("particle_effects")
        @Expose
        var particleEffectsData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * can be called multiple times
         * ```
         * soundEffect("smoke", "namespace:smoke_particle")
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun particleEffect(name: String, particle: String) {
            particleEffectsData = (particleEffectsData ?: mutableMapOf()).apply {
                put(name, particle)
            }
        }

        @SerializedName("sound_effects")
        @Expose
        var soundEffectsData: MutableMap<String, String>? = null
            @MonsteraBuildSetter set

        /**
         * can be called multiple times
         * ```
         * soundEffect("attack_1", "mob.entity.attack_1")
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun soundEffect(name: String, sound: String) {
            soundEffectsData = (soundEffectsData ?: mutableMapOf()).apply {
                put(name, sound)
            }
        }

        @SerializedName("scripts")
        @Expose
        var scriptsData: ResEntityScripts? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun scripts(data: ResEntityScripts.() -> Unit) {
            scriptsData = (scriptsData ?: ResEntityScripts()).apply(data)
        }
    }
}