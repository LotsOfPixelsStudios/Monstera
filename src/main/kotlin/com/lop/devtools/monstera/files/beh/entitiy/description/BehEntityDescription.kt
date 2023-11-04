package com.lop.devtools.monstera.files.beh.entitiy.description

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.properties.EntityProperties

class BehEntityDescription : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        val scripts = BehEntityDescScripts()
        val animations = BehEntityDescAnimations()
        val properties = EntityProperties()

        override fun getData(): MutableMap<String, Any> {
            identifier?.let { unsafe.general["identifier"] = it }
            unsafe.general["is_spawnable"] = isSpawnable
            unsafe.general["is_summonable"] = isSummonable
            unsafe.general["is_experimental"] = isExperimental
            runtimeIdentifier?.let { unsafe.general["runtime_identifier"] = "minecraft:${it.toString().lowercase()}" }

            if (unsafe.scripts.unsafe.getData().isNotEmpty())
                unsafe.general["scripts"] = unsafe.scripts.unsafe.getData()
            if (unsafe.animations.unsafe.getData().isNotEmpty())
                unsafe.general["animations"] = unsafe.animations.unsafe.getData()
            if (unsafe.properties.unsafe.getData().isNotEmpty())
                unsafe.general["properties"] = unsafe.properties.unsafe.getData()

            return unsafe.general
        }
    }

    var identifier: String? = null
    var isSpawnable: Boolean = true
    var isSummonable: Boolean = true
    var isExperimental: Boolean = false

    var runtimeIdentifier: RuntimeIdentifier? = null

    /**
     * 0..1
     *
     * activate animations defined in animations()
     * @sample sampleScript
     */
    fun scripts(settings: BehEntityDescScripts.() -> Unit) {
        unsafe.scripts.apply(settings)
    }

    /**
     * 0..1
     * @sample sampleAnim
     */
    fun animations(settings: BehEntityDescAnimations.() -> Unit) {
        unsafe.animations.apply(settings)
    }

    /**
     * set new properties to an entity
     *
     * ```
     * enum("name") { }
     * bool("name") { }
     * int("name") { }
     * float("name") { }
     * ```
     */
    fun properties(data: EntityProperties.() -> Unit) {
        unsafe.properties.apply(data)
    }

    private fun sampleScript() {
        scripts {
            animate(arrayListOf("testCon"))
        }
    }

    private fun sampleAnim() {
        animations {
            addAnim("testCon", "controller.animation.test_name.attack")
            addAnim("testAnim", "animation.test_name.timer")
        }
    }
}