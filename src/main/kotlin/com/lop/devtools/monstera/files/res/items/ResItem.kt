package com.lop.devtools.monstera.files.res.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class ResItem: MonsteraBuildableFile {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename.removeSuffix(".json").replace("-", "_").replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.resItem ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for res item file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for res item file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.resItem ?: "1.10.0",
                this
            )
        )
        return Result.success(target)
    }

    /**
     * load json item with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.10.0",

        @SerializedName("minecraft:client_entity")
        @Expose
        var entity: ResItem
    )

    @SerializedName("description")
    @Expose
    var description: Description? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * description {
     *     identifier = "namespace:my_entity"
     *     category = "Equipment"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        description = (description ?: Description()).apply(data)
    }

    class Description {
        @SerializedName("components")
        @Expose
        var identifier: String? = null

        @SerializedName("category")
        @Expose
        var category: String? = null
    }

    @SerializedName("components")
    @Expose
    var components: Components? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * components {
     *
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun components(data: Components.() -> Unit) {
        components = (components ?: Components()).apply(data)
    }

    class Components {

        @SerializedName("minecraft:icon")
        @Expose
        var icon: String? = null
            @MonsteraBuildSetter set

        @SerializedName("minecraft:render_offsets")
        @Expose
        var renderOffset: String? = null

        @OptIn(MonsteraBuildSetter::class)
        fun icon(iconName: String, iconPath: String = "textures/items/$iconName", addon: Addon? = null) {
            addon?.let {
                TextureIndex.instance(it).textures.add(iconPath)
                ItemTextureIndex.instance(it).addItemTexture(iconName, iconPath)
            }

            icon = iconName
        }

        @OptIn(MonsteraBuildSetter::class)
        fun icons(iconName: String, iconPath: ArrayList<String>, addon: Addon? = null) {
            addon?.let {
                iconPath.forEach { path ->
                    TextureIndex.instance(it).textures.add(path)
                }
                ItemTextureIndex.instance(it).addItemTexture(iconName, iconPath)
            }
            icon = iconName
        }
    }
}