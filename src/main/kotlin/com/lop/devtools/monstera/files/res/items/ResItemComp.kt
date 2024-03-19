package com.lop.devtools.monstera.files.res.items

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex

class ResItemComp: MonsteraFile {
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
    }

    fun icon(iconName: String, iconPath: String = "textures/items/$iconName", addon: Addon? = null) {
        addon?.let {
            TextureIndex.instance(it).textures.add(iconPath)
            ItemTextureIndex.instance(it).addItemTexture(iconName, iconPath)
        }

        unsafe.general["minecraft:icon"] = iconName
    }

    fun icons(iconName: String, iconPath: ArrayList<String>, addon: Addon? = null) {
        addon?.let {
            iconPath.forEach { path ->
                TextureIndex.instance(it).textures.add(path)
            }
            ItemTextureIndex.instance(it).addItemTexture(iconName, iconPath)
        }
        unsafe.general["minecraft:icon"] = iconName
    }

    fun renderOffset(value: String) {
        unsafe.general.apply {
            put("minecraft:render_offsets", value)
        }
    }
}