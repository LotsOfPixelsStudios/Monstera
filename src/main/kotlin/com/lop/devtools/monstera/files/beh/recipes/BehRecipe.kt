@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.recipes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.sanetiseFilename
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class BehRecipeShaped : MonsteraBuildableFile, MonsteraRawFile() {
    var data = BehRecipe()

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.behRecipe ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for crafting recipe file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for crafting recipe file '$filename' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanetiseFilename(filename, "json"),
            BehRecipe.FileHeaderShaped(
                version ?: Addon.active?.config?.formatVersions?.behRecipe ?: "1.17.41",
                data
            )
        )
        return Result.success(target)
    }
}

class BehRecipeFurnace : MonsteraBuildableFile {
    var data = BehRecipe()

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behRecipe ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for furnace recipe file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for furnace recipe file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            BehRecipe.FileHeaderFurnace(
                version ?: Addon.active?.config?.formatVersions?.behRecipe ?: "1.17.41",
                data
            )
        )
        return Result.success(target)
    }
}

class BehRecipeBrewingMix : MonsteraBuildableFile {
    var data = BehRecipe()

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behRecipe ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for brewing mix recipe file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for brewing mix recipe file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            BehRecipe.FileHeaderBrewingMix(
                version ?: Addon.active?.config?.formatVersions?.behRecipe ?: "1.17.41",
                data
            )
        )
        return Result.success(target)
    }
}

class BehRecipeBrewingContainer : MonsteraBuildableFile {
    var data = BehRecipe()

    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behRecipe ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for brew container recipe file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for brew container recipe file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            BehRecipe.FileHeaderBrewingContainer(
                version ?: Addon.active?.config?.formatVersions?.behRecipe ?: "1.17.41",
                data
            )
        )
        return Result.success(target)
    }
}

class BehRecipe {
    /**
     * load json blocks with this class
     */
    data class FileHeaderShaped(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.17.41",

        @SerializedName("minecraft:recipe_shaped")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var recipe: BehRecipe
    ): MonsteraRawFile()

    data class FileHeaderFurnace(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.17.41",

        @SerializedName("minecraft:recipe_furnace")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var recipe: BehRecipe
    ): MonsteraRawFile()

    data class FileHeaderBrewingMix(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.17.41",

        @SerializedName("minecraft:recipe_brewing_mix")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var recipe: BehRecipe
    ): MonsteraRawFile()

    data class FileHeaderBrewingContainer(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "1.17.41",

        @SerializedName("minecraft:recipe_brewing_container")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var recipe: BehRecipe
    ): MonsteraRawFile()

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: Description? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun description(identifier: String) {
        descriptionData = Description().apply { this.identifier = identifier }
    }

    class Description: MonsteraRawFile() {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null
    }

    /**
     * used for brewing
     */
    @SerializedName("input")
    @Expose
    var input: String? = null

    /**
     * used for brewing
     */
    @SerializedName("reagent")
    @Expose
    var reagent: String? = null

    @SerializedName("tags")
    @Expose
    var tags: MutableList<RecipeTags>? = null

    @SerializedName("pattern")
    @Expose
    var pattern: MutableList<String>? = null

    @SerializedName("key")
    @Expose
    var key: MutableMap<String, String>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun addKey(key: String, item: String) {
        this.key = (this.key ?: mutableMapOf()).apply { put(key, item) }
    }

    @SerializedName("unlock")
    @Expose
    var unlockData: MutableList<ItemInfo>? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * unlockRequirement {
     *     item = "minecraft:planks"
     *     data(2)
     *     count = 3
     *
     *     item = "minecraft:spawn_egg"
     *     data("q.get_actor_info_id('minecraft:chicken')")
     *
     *     item = "minecraft:potion_type:strength"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun unlockRequirement(option: ItemInfo.() -> Unit) {
        unlockData = (unlockData ?: mutableListOf()).apply { add(ItemInfo().apply(option)) }
    }

    @SerializedName("result")
    @Expose
    var resultData: MutableList<ItemInfo>? = null
        @MonsteraBuildSetter set


    /**
     * can be called multiple times if there should be more than one item as a result
     * ```
     * result {
     *     item = "minecraft:planks"
     *     data(2)
     *     count = 3
     *
     *     item = "minecraft:spawn_egg"
     *     data("q.get_actor_info_id('minecraft:chicken')")
     *
     *     item = "minecraft:potion_type:strength"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun result(data: ItemInfo.() -> Unit) {
        resultData = (resultData ?: mutableListOf()).apply { add(ItemInfo().apply(data)) }
    }

    @SerializedName("ingredients")
    @Expose
    var ingredientsData: MutableList<ItemInfo>? = null
        @MonsteraBuildSetter set

    /**
     * can be called multiple times if there should be more than one ingredient
     * ```
     * ingredient {
     *     item = "minecraft:planks"
     *     data(2)
     *     count = 3
     *
     *     item = "minecraft:spawn_egg"
     *     data("q.get_actor_info_id('minecraft:chicken')")
     *
     *     item = "minecraft:potion_type:strength"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun ingredient(data: ItemInfo.() -> Unit) {
        ingredientsData = (ingredientsData ?: mutableListOf()).apply { add(ItemInfo().apply(data)) }
    }

    open class ItemInfo {
        @SerializedName("item")
        @Expose
        var item: String? = null

        @SerializedName("data")
        @Expose
        var data: Any? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun data(value: Int) {
            data = value
        }

        @OptIn(MonsteraBuildSetter::class)
        fun data(value: String) {
            data = value
        }

        @SerializedName("context")
        @Expose
        var context: String? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun context() {
            context = "PlayerInWater"
        }

        @SerializedName("count")
        @Expose
        var count: Int? = null
    }

}

enum class RecipeTags {
    FURNACE,
    BLAST_FURNACE,
    SMOKER,
    CAMPFIRE,
    SOUL_CAMPFIRE,
    CRAFTING_TABLE,
    STONECUTTER,
    SMITHING_TABLE,
    BREWING_STAND;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
