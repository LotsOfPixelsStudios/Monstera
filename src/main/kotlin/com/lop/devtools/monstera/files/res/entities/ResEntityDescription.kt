package com.lop.devtools.monstera.files.res.entities

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.lang.langKey
import com.lop.devtools.monstera.files.res.entities.comp.*

class ResEntityDescription : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        val material = ResEntityMaterial()
        val geometries = ResEntityGeometries()
        val textures = ResEntityTextures()
        val animations = ResEntityAnimations()
        val controllers = ResEntityAnimationControllers()
        val renderControllers = ResEntityRenderCon()
        val locators = ResEntityLocators()
        val spawnEgg = ResEntitySpawnEgg()
        val particleEffect = ResEntityParticleEffect()
        val soundEffect = ResEntitySoundEffect()
        val scripts = ResEntityScripts()

        override fun getData(): MutableMap<String, Any> {
            if (unsafe.material.unsafe.getData().isNotEmpty()) unsafe.general["materials"] =
                unsafe.material.unsafe.getData()
            if (unsafe.geometries.unsafe.getData().isNotEmpty()) unsafe.general["geometry"] =
                unsafe.geometries.unsafe.getData()
            if (unsafe.textures.unsafe.getData().isNotEmpty()) unsafe.general["textures"] =
                unsafe.textures.unsafe.getData()
            if (unsafe.animations.unsafe.getData().isNotEmpty()) unsafe.general["animations"] =
                unsafe.animations.unsafe.getData()
            if (unsafe.controllers.unsafe.getData().isNotEmpty()) unsafe.general["animation_controllers"] =
                unsafe.controllers.unsafe.getData()
            if (unsafe.renderControllers.unsafe.getData().isNotEmpty()) unsafe.general["render_controllers"] =
                unsafe.renderControllers.unsafe.getData()
            if (unsafe.locators.unsafe.getData().isNotEmpty()) unsafe.general["locators"] =
                unsafe.locators.unsafe.getData()
            if (unsafe.spawnEgg.unsafe.getData().isNotEmpty()) unsafe.general["spawn_egg"] =
                unsafe.spawnEgg.unsafe.getData()
            enableAttachment?.let { unsafe.general["enable_attachables"] = it }
            if (unsafe.particleEffect.unsafe.getData().isNotEmpty()) unsafe.general["particle_effects"] =
                unsafe.particleEffect.unsafe.getData()
            if (unsafe.soundEffect.unsafe.getData().isNotEmpty()) unsafe.general["sound_effects"] =
                unsafe.soundEffect.unsafe.getData()
            if (unsafe.scripts.unsafe.getData().isNotEmpty()) unsafe.general["scripts"] =
                unsafe.scripts.unsafe.getData()

            return unsafe.general
        }
    }

    var enableAttachment: Boolean? = null

    /**
     * 1
     *
     * every entity need a identifier which can be called in different parts of the addon
     * naming convention: projectName:entityName
     * @param identifier: name
     * @sample sampleIdentifier
     */
    fun identifier(identifier: String, langValue: String = identifier) {
        unsafe.general.apply {
            put("identifier", identifier)
        }

        //add a lang key for the entity itself, so if you kill it this name will show
        langKey("entity.$identifier.name", langValue)
    }

    /**
     * 0..1
     *
     * use with caution!
     *
     * This can break the texture of the entity
     * @link: https://compass.minecraft.partners/hc/en-us/articles/360026720393-Entities#min_engine_version
     * @sample sampleMineEngineVersion
     */
    fun mineEngineVersion(version: String = "1.8.0") {
        unsafe.general.apply {
            put("min_engine_version", version)
        }
    }

    /**
     * 1
     *
     * map of Materials
     * @sample sampleMaterial
     */
    fun materials(settings: ResEntityMaterial.() -> Unit) {
        unsafe.material.apply(settings)
    }

    /**
     * 1
     *
     * map of geos
     * @sample sampleGeo
     */
    fun geometries(settings: ResEntityGeometries.() -> Unit) {
        unsafe.geometries.apply(settings)
    }

    /**
     * 1
     *
     * map of textures
     */
    fun textures(settings: ResEntityTextures.() -> Unit) {
        unsafe.textures.apply(settings)
    }

    /**
     * 0..1
     *
     * map of animations
     * @sample sampleAnim
     */
    fun animations(settings: ResEntityAnimations.() -> Unit) {
        unsafe.animations.apply(settings)
    }

    /**
     * 0..1
     *
     * list of controllers
     * @sample sampleAnimCon
     */
    fun animationControllers(settings: ResEntityAnimationControllers.() -> Unit) {
        unsafe.controllers.apply(settings)
    }

    /**
     * 1
     *
     * @param controllers: list of all the controllers that are used (usually only one)
     * @sample sampleRenderConList
     * @sample sampleRenderCon
     */
    fun renderControllers(controllers: ArrayList<String> = arrayListOf("controller.render.default")) {
        unsafe.renderControllers.apply {
            controllers.forEach {
                renderCon(it)
            }
        }
    }

    /**
     * 1
     *
     * @param settings: set every controller by hand (usually only one)
     * @sample sampleRenderConList
     * @sample sampleRenderCon
     */
    fun renderControllers(settings: ResEntityRenderCon.() -> Unit) {
        unsafe.renderControllers.apply(settings)
    }

    /**
     * 0..1
     *
     * @sample sampleLocators
     */
    fun locators(settings: ResEntityLocators.() -> Unit) {
        unsafe.locators.apply(settings)
    }

    /**
     * 1
     *
     * @sample sampleSpawnEgg
     */
    fun spawnEgg(langValue: String = "", langKey: String = "", settings: ResEntitySpawnEgg.() -> Unit) {
        unsafe.spawnEgg.apply(settings)
        if (unsafe.general.contains("identifier") && langKey == "") {
            if (unsafe.spawnEgg.displayName != null) {
                langKey(
                    "item.spawn_egg.entity.${unsafe.general["identifier"]}.name",
                    unsafe.spawnEgg.displayName!!
                )
            } else if (langValue == "") {
                langKey(
                    "item.spawn_egg.entity.${unsafe.general["identifier"]}.name",
                    unsafe.general["identifier"].toString().substring(startIndex = 3)
                )
            } else {
                langKey(
                    "item.spawn_egg.entity.${unsafe.general["identifier"]}.name",
                    langValue
                )
            }
        } else {
            langKey(langKey, langValue)
        }
    }

    /**
     * 1
     *
     * This determines if the entity can equip attachables when this is set to true. This allows the entity to render armor.
     */
    fun enableAttachment(enable: Boolean = false) {
        unsafe.general.apply {
            put("enable_attachables", enable)
        }
    }

    /**
     * 0..*
     *
     * @sample sampleParticle
     */
    fun particleEffects(settings: ResEntityParticleEffect.() -> Unit) {
        unsafe.particleEffect.apply(settings)
    }

    /**
     * 0..*
     *
     * @sample sampleSound
     */
    fun soundEffects(settings: ResEntitySoundEffect.() -> Unit) {
        unsafe.soundEffect.apply(settings)
    }

    /**
     * 0..1
     *
     * Scripts allow players to use MoLang to compute calculations once and store that value. This value than can be used over and over again without the need to constantly recompute the calculations. Scripts currently support pre - animation and scale.More script types will be added later.
    -Pre-animation scripts are evaluated immediately before animations are processed.
    -Scale sets the scale of the mob's geometry.
     * @sample sampleScripts
     */
    fun scripts(settings: ResEntityScripts.() -> Unit) {
        unsafe.scripts.apply(settings)
    }

    /*
    #####################################################################################
    Samples:
    #####################################################################################
     */
    private fun sampleIdentifier() {
        identifier("pa:bird")
    }

    private fun sampleMineEngineVersion() {
        mineEngineVersion("1.8.0")
    }

    private fun sampleMaterial() {
        materials {
            material(material = "pig")  //identifier = default
            material(identifier = "transparent", material = "entity_custom")
        }
    }

    private fun sampleGeo() {
        geometries {
            geometry(geo = "geometry.pig.v1.8")  //identifier = default
            geometry(identifier = "transparent", geo = "geometry.pig")
        }
    }

    private fun sampleAnim() {
        animations {
            animation(identifier = "setup", anim = "animation.pig.setup")  //identifier = default
            animation(identifier = "walk", anim = "animation.quadruped.walk")
        }
    }

    private fun sampleAnimCon() {
        animationControllers {
            animation("setup", "setup")
            animation("fly", "fly")
        }
    }

    private fun sampleRenderConList() {
        renderControllers(arrayListOf("controller.render.pig", "controller.render.pig2"))
    }

    private fun sampleRenderCon() {
        renderControllers {
            renderCon("controller.render.pig")
            renderCon("controller.render.pig2")
        }
    }

    private fun sampleLocators() {
        locators {
            lead {
                attach(x = 1.0f, y = 1.0f, z = 1.0f)
            }
        }
    }

    private fun sampleSpawnEgg() {
        spawnEgg {
            //use only one
            eggByTexture(textureIndex = 1)  //use spawn egg
            unsafe.eggByTexture("compass")  //use custom texture
            eggByColor("#53443E", "#2E6854") //use spawn egg with custom colors
        }
    }

    private fun sampleScripts() {
        scripts {
            preAnim(arrayListOf("var.setSth", "var.tan"))

            script("scale", "0.35")
            script("scale2", "0.31")
        }
    }

    private fun sampleParticle() {
        particleEffects {
            particleEffect("exhaust", "gl:drone_exhaust")
        }
    }

    private fun sampleSound() {
        soundEffects {
            soundEffect("door_open", "random.door_open")
        }
    }
}