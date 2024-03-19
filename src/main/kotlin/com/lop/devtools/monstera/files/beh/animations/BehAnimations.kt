package com.lop.devtools.monstera.files.beh.animations

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class BehAnimations: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            return unsafe.general
        }

        fun build(
            filename: String,
            path: Path,
            version: String = "1.8.0"
        ) {
            val sanFile = filename
                .removeSuffix(".json")
                .replace("-", "_")
                .replace(" ", "_")
            MonsteraBuilder.buildTo(
                path, "$sanFile.json", mutableMapOf(
                    "format_version" to version,
                    "animations" to getData()
                )
            )
        }
    }

    /**
     * 1..*
     *
     * @param name: name of the animation, naming convention: "animation.<entityName>.<animName>"
     * notice: you can leave out "animation." you only have to put <entityName>.<animName>
     * @sample sample
     */
    fun animation(name: String, settings: BehAnimation.() -> Unit) {
        val behAnimation = BehAnimation().apply { settings(this) }
        unsafe.general.apply {
            if(!name.contains("animation.")) {
                put("animation.$name",behAnimation.unsafe.getData())
            } else{
                put(name,behAnimation.unsafe.getData())
            }
        }
    }

    /**
     * Sample
     */
    private fun sample() {
        animation("animation.player.timer_backpack") {
            timeline { /*...*/ }
            animLength = 0.2f
        }
    }
}