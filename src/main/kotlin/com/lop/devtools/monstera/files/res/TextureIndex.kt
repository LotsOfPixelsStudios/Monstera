package com.lop.devtools.monstera.files.res

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.MonsteraBuilder

class TextureIndex {
    companion object {
        private val instances = mutableMapOf<Int, TextureIndex>()

        fun instance(addon: Addon): TextureIndex {
            return instances[addon.hashCode()] ?: TextureIndex().also {
                instances[addon.hashCode()] = it
            }
        }
    }

    val textures = mutableListOf<String>()

    fun build(addon: Addon) {
        MonsteraBuilder.legacyBuilder(addon.config.paths.resTextures, "textures_list.json", textures)
    }
}

class ItemTextureIndex {
    companion object {
        private val instances = mutableMapOf<Int, ItemTextureIndex>()

        fun instance(addon: Addon): ItemTextureIndex {
            return instances[addon.hashCode()] ?: ItemTextureIndex().also {
                instances[addon.hashCode()] = it
            }
        }
    }

    val textureMap = mutableMapOf<String, Any>()

    fun addItemTexture(itemName: String, itemPath: String) {
        textureMap[itemName] = mapOf("textures" to itemPath)
    }

    fun addItemTexture(itemName: String, itemPath: ArrayList<String>) {
        textureMap[itemName] = mapOf("textures" to itemPath)
    }

    fun getData(): MutableMap<String, Any> {
        return mutableMapOf(
            "resource_pack_name" to "pack.name",
            "texture_name" to "atlas.items",
            "texture_data" to textureMap
        )
    }

    fun build(addon: Addon) {
        particleTextureList(addon)
        MonsteraBuilder.legacyBuilder(addon.config.paths.resTextures, "item_texture.json", getData())
    }
}