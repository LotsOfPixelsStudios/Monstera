package com.lop.devtools.monstera.addon.sound

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.res.sounds.ResSoundDefs
import com.lop.devtools.monstera.files.res.sounds.Sounds

class SoundUtil(val addon: Addon): MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val soundDefs = ResSoundDefs()
        val sounds = Sounds()

        /**
         * does build the files, will rerun nothing
         */
        override fun getData(): MutableMap<String, Any> {
            build()
            return mutableMapOf()
        }

        fun build() {
            soundDefs.build("", path = addon.config.paths.resSounds)
            sounds.build("", path = addon.config.resPath)
        }
    }

    fun soundsDefinitions(sounds: ResSoundDefs.() -> Unit) {
        unsafe.soundDefs.apply(sounds)
    }

    fun categorySounds(sounds: Sounds.() -> Unit) {
        unsafe.sounds.apply(sounds)
    }
}