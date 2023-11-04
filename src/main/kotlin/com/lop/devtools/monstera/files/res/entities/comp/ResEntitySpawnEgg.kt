package com.lop.devtools.monstera.files.res.entities.comp

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex
import java.awt.Color
import java.io.File
import java.nio.file.Path

class ResEntitySpawnEgg: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        /**
         * access to all defined animations
         */
        val general = mutableMapOf<String, Any>()

        /**
         * 1
         *
         * @param textureName: texture name defined in the item texture index
         */
        fun eggByTexture(textureName: String) {
            unsafe.general["textures"] = textureName
        }

        override fun getData(): MutableMap<String, Any> {
            return unsafe.general
        }
    }

    var displayName: String? = null

    /**
     * 1
     *
     * @param texture: texture name defined in the item texture index
     * @param textureIndex: use this if a texture has multiple textures (like the spawn_egg)
     */
    fun eggByTexture(texture: String = "spawn_egg", textureIndex: Int) {
        unsafe.general.apply {
            put("texture", texture)
            put("texture_index", textureIndex)
        }
    }

    fun eggByTexture(addon: Addon, textureName: String, path: String = "textures/items/$textureName") {
        ItemTextureIndex.instance(addon).textureMap[textureName] = path
        TextureIndex.instance(addon).textures.add(path)
        unsafe.eggByTexture(textureName)
    }

    /**
     * 1
     *
     * copies the file into the correct spot and generates the necessary paths for mc
     */
    fun eggByFile(file: File, addon: Addon, resTexturePath: Path = addon.config.paths.resTextures) {
        val uniqueName = getUniqueFileName(file)
        val target = resTexturePath.resolve("monstera").resolve(uniqueName).toFile()
        file.copyTo(target, true)
        val fileName = uniqueName.removeSuffix(".png")
        ItemTextureIndex.instance(addon).addItemTexture(fileName, "textures/monstera/${fileName}")
        TextureIndex.instance(addon).textures.add("textures/monstera/${fileName}")
        unsafe.general["texture"] = fileName
    }

    /**
     * 1
     *
     * @param baseColor: background color of the spawn egg as a Hex String like ("#53443E")
     * @param overlayColor: details of the egg texture like ("#2E6854")
     */
    fun eggByColor(baseColor: String, overlayColor: String) {
        unsafe.general.apply {
            put("base_color", baseColor)
            put("overlay_color", overlayColor)
        }
    }


    /**
     * 1
     *
     * @param baseColor: background color of the spawn egg as a Hex String like ("#53443E")
     * @param overlayColor: details of the egg texture like ("#2E6854")
     */
    fun eggByColor(baseColor: Color, overlayColor: Color) {
        unsafe.general.apply {
            put("base_color", "#${Integer.toHexString(baseColor.rgb)}")
            put("overlay_color", "#${Integer.toHexString(overlayColor.rgb)}")
        }
    }

    fun test() {
        unsafe.eggByTexture("monstera.default_texture")
    }
}