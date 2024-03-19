@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.blocks.components.BlockComponents
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class BehBlocks : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behBlocks ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for block file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for block file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behBlock ?: "1.20.50",
                this
            )
        )
        return Result.success(target)
    }

    /**
     * load json blocks with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.20.50",

        @SerializedName("minecraft:block")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var block: BehBlocks
    ): MonsteraRawFile()

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: BlockDescription? = null
        @MonsteraBuildSetter set

    @SerializedName("components")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var componentsData: BlockComponents? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun description(data: BlockDescription.() -> Unit) {
        descriptionData = (descriptionData ?: BlockDescription()).apply(data)
    }

    /**
     * ```
     * geometry()
     * materialInstance()
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun components(data: BlockComponents.() -> Unit) {
        componentsData = (componentsData ?: BlockComponents()).apply(data)
    }
}