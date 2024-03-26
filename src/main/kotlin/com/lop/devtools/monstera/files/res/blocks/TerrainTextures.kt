package com.lop.devtools.monstera.files.res.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.*
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile

class TerrainTextures : InvokeBeforeEnd, MonsteraRawFile() {
    @SerializedName("texture_name")
    @Expose
    var textureName: String = "atlas.terrain"

    @SerializedName("resource_pack_name")
    @Expose
    var resourcePackName: String = "pack.name"

    @SerializedName("num_mip_levels")
    @Expose
    var numMipLevels = 4

    @SerializedName("padding")
    @Expose
    var padding = 8

    @SerializedName("texture_data")
    @Expose
    var textureData: MutableMap<String, Texture>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun addBlockTexture(name: String, texturePath: String) {
        textureData = (textureData ?: mutableMapOf()).apply {
            put(name, Texture().apply { textures = texturePath })
        }
    }

    open class Texture {
        @SerializedName("textures")
        @Expose
        var textures: String? = null
    }

    val unsafe = Unsafe()

    companion object {
        private val instances = mutableMapOf<Int, TerrainTextures>()

        fun instance(addon: Addon): TerrainTextures {
            if (!instances.containsKey(addon.hashCode())) {
                instances[addon.hashCode()] = TerrainTextures()
            }
            return instances[addon.hashCode()]!!
        }
    }

    inner class Unsafe {
        fun buildFile(addon: Addon) {
            MonsteraBuilder.buildTo(addon.config.paths.resTextures, "terrain_texture.json", this@TerrainTextures)
        }
    }

    override fun invoke(addon: Addon) {
        unsafe.buildFile(addon)
    }
}