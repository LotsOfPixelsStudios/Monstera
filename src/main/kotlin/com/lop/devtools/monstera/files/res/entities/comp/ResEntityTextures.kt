package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.TextureIndex
import java.io.File

class ResEntityTextures {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        /**
         * 1..*
         *
         * @param state: callable from render controller
         * @param texturePath: path to texture like "textures/entity/furniture/satellite_dish/satellite_dish_1",
         */
        fun texture(state: String = "default", texturePath: String) {
            unsafe.general[state] = texturePath
        }

        fun getData(): Map<String, Any> {
            return unsafe.general
        }
    }

    fun texture(state: String, file: File, addon: Addon) {
        val targetName = getUniqueFileName(file)
        val targetPath = addon.config.paths.resTextures.resolve("monstera").resolve("$targetName.png")
        TextureIndex.instance(addon).textures.add("textures/monstera/$targetName")
        file.copyTo(targetPath.toFile(), true)
        unsafe.general[state] = "textures/monstera/$targetName"
    }
}