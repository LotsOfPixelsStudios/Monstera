package com.lop.devtools.monstera.files.res.blocks

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder

class TerrainTextures : MonsteraFile, InvokeBeforeEnd {
    override val unsafe = Unsafe()

    companion object {
        private val instances = mutableMapOf<Int, TerrainTextures>()

        fun instance (addon: Addon): TerrainTextures {
            if(!instances.containsKey(addon.hashCode())) {
                instances[addon.hashCode()] = TerrainTextures()
            }
            return instances[addon.hashCode()]!!
        }
    }

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val textureData = mutableMapOf<String, MutableMap<String, String>>()

        override fun getData(): MutableMap<String, Any> {
            general["num_mip_levels"] = numMipLevels
            general["padding"] = padding
            general["resource_pack_name"] = resourcePackName
            general["texture_data"] = textureData

            return general
        }

        fun buildFile(addon: Addon) {
            MonsteraBuilder.buildTo(addon.config.paths.resTextures, "terrain_texture.json", getData())
        }
    }

    var numMipLevels = 4
    var padding = 8
    var resourcePackName = "pack.name"


    fun addBlockTexture(name: String, texturePath: String) {
        unsafe.textureData[name] = mutableMapOf("textures" to texturePath)
    }

    override fun invoke(addon: Addon) {
        unsafe.buildFile(addon)
    }
}