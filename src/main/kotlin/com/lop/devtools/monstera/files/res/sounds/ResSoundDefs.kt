package com.lop.devtools.monstera.files.res.sounds

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class ResSoundDefs : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()
        val soundDefs = mutableMapOf<String, ResSoundDef>()

        override fun getData(): MutableMap<String, Any> {
            if(soundDefs.isNotEmpty()) {
                soundDefs.forEach { (k,v) ->
                    general[k] = v.unsafe.getData()
                }
            }
            return general
        }

        fun build(
            filename: String = "sound_definitions.json",
            path: Path,
        ) {
            MonsteraBuilder.buildTo(
                path, filename, getData()
            )
        }
    }

    /**
     * 0..1
     *
     * ```
     * newSoundDef("tp.test.drive") {
     *     category(SoundCategory.BLOCK)
     *     maxDistance(2f)
     *     minDistance(1f)
     *     //can be called multiple times
     *     sound {
     *         name("sounds/test/test1")
     *         volume(1f)
     *         pitch(1f)
     *     }
     *     sound("sounds/test/test")
     *     sound("sounds/test/test2")
     * }
     * ```
     *
     * @param soundIdentifier like "music.cd.game"
     * @param settings
     */
    fun newSoundDef(soundIdentifier: String, settings: ResSoundDef.() -> Unit) {
        if(unsafe.soundDefs.containsKey(soundIdentifier)) {
            unsafe.soundDefs[soundIdentifier]!!.apply(settings)
        } else {
            unsafe.soundDefs[soundIdentifier] = ResSoundDef().apply(settings)
        }
    }
}