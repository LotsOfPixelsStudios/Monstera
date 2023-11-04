package com.lop.devtools.monstera.files.res.blocks

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.MonsteraBuilder
import java.nio.file.Path

class BlockDefs : MonsteraFile, InvokeBeforeEnd {
    override val unsafe = Unsafe()

    companion object {
        private val instances = mutableMapOf<Int, BlockDefs>()

        fun instance(addon: Addon): BlockDefs {
            return instances[addon.hashCode()] ?: BlockDefs().also {
                instances[addon.hashCode()] = it
            }
        }
    }

    inner class Unsafe : MonsteraUnsafeMap {
        val blockDefs = mutableMapOf<String, BlockDefinition>()

        override fun getData(): MutableMap<String, Any> {
            return blockDefs.map { it.key to it.value.unsafe.getData() }.toMap().toMutableMap()
        }

        fun build(resPath: Path) {
            MonsteraBuilder.buildTo(resPath, "blocks.json", getData())
        }
    }

    override fun invoke(addon: Addon) {
        unsafe.build(addon.config.resPath)
    }

    /**
     * add or apply a definition for a given block identifier
     *
     * ```
     * addDefinition("ancient_debris") {
     *     sound("ancient_debris")
     *     textures {
     *          down("ancient_debris_top")
     *          east("ancient_debris_side")
     *          north("ancient_debris_side")
     *          south("ancient_debris_side")
     *          up("ancient_debris_top")
     *          west("ancient_debris_side")
     *     }
     *     //or to apply all sites
     *     textures("texture")
     * }
     * ```
     *
     * @param blockIdentifier the identifier of the block but without the `minecraft:`
     * @param data the texture and sound data information
     */
    fun addDefinition(blockIdentifier: String, data: BlockDefinition.() -> Unit) {
        unsafe.blockDefs[blockIdentifier]?.apply(data)
        if (unsafe.blockDefs[blockIdentifier] == null) {
            unsafe.blockDefs[blockIdentifier] = BlockDefinition().apply(data)
        }
    }

    class BlockDefinition : MonsteraFile {
        override val unsafe: Unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()
            val textureData = BlockTextureDefinition()

            override fun getData(): MutableMap<String, Any> {
                if (textureData.unsafe.getData().isNotEmpty()) {
                    unsafe.general["textures"] = textureData.unsafe.getData()
                }
                return general
            }
        }

        var sound: String = ""
            set(value) {
                unsafe.general["sound"] = value
                field = value
            }
        var carriedTextures = ""
            set(value) {
                unsafe.general["carried_textures"] = value
                field = value
            }
        var isotropic = false
            set(value) {
                unsafe.general["isotropic"] = value
                field = value
            }
        var brightnessGamma: Number = 1
            set(value) {
                unsafe.general["brightness_gamma"] = value
                field = value
            }

        fun texture(id: String) {
            unsafe.general["textures"] = id
        }

        /**
         * ```
         * up("texture")
         * down("texture")
         * east("texture")
         * west("texture")
         * south("texture")
         * north("texture")
         * side("texture")
         * ```
         */
        fun texture(data: BlockTextureDefinition.() -> Unit) {
            unsafe.textureData.apply(data)
        }
    }

    class BlockTextureDefinition : MonsteraFile {
        override val unsafe: Unsafe = Unsafe()

        inner class Unsafe : MonsteraUnsafeMap {
            val general = mutableMapOf<String, Any>()

            override fun getData(): MutableMap<String, Any> {
                return general
            }
        }

        fun up(texture: String) {
            unsafe.general["up"] = texture
        }

        fun down(texture: String) {
            unsafe.general["down"] = texture
        }

        fun side(texture: String) {
            unsafe.general["side"] = texture
        }

        fun north(texture: String) {
            unsafe.general["north"] = texture
        }

        fun south(texture: String) {
            unsafe.general["south"] = texture
        }

        fun west(texture: String) {
            unsafe.general["west"] = texture
        }

        fun east(texture: String) {
            unsafe.general["east"] = texture
        }
    }
}