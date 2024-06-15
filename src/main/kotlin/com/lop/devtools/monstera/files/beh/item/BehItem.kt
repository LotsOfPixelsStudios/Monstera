@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.beh.item

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
import java.nio.file.Path

class BehItem : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.behItems ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for item file '$filename' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for item file '$filename' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            sanetiseFilename(filename, "json"),
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behItem ?: "1.50.0",
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
        var formatVersion: String = "",

        @SerializedName("minecraft:item")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var block: BehItem
    ) : MonsteraRawFile()

    @SerializedName("description")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var descriptionData: Description? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * description {
     *      identifier = "namespace:my_item"
     *      menuCategory = Category.ITEMS
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun description(data: Description.() -> Unit) {
        descriptionData = (descriptionData ?: Description()).apply(data)
    }

    @SerializedName("components")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var componentsData: BehItemComponents = BehItemComponents()
        @MonsteraBuildSetter set

    /**
     * Allow Off Hand
     *
     * Block Placer
     *
     * Can Destroy In Creative
     *
     * Cooldown
     *
     * Damage
     *
     * Digger
     *
     * Display Name
     *
     * Durability
     *
     * Enchantable
     *
     * Entity Placer
     *
     * Food
     *
     * Fuel
     *
     * Glint
     *
     * Hand Equipped
     *
     * Hover Text Color
     *
     * Icon
     *
     * Interact Button
     *
     * Liquid Clipped
     *
     * Max Stack Size
     *
     * Projectile
     *
     * Record
     *
     * Repairable
     *
     * Shooter
     *
     * Should Despawn
     *
     * Stacked By Data
     *
     * Tags
     *
     * Throwable
     *
     * Use Animation
     *
     * Use Modifiers
     *
     * Wearable
     */
    fun components(data: BehItemComponents.() -> Unit) {
        componentsData.apply(data)
    }

    class Description : MonsteraRawFile() {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null

        @SerializedName("menu_category")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var menuCategoryData: CategoryData? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        @Deprecated("", ReplaceWith("menuCategory { }"))
        var menuCategory = Category.ITEMS
            set(value) {
                menuCategoryData = CategoryData().also { it.category = value }
                field = value
            }

        /**
         * ```
         * category = Category.EQUIPMENT
         * group = "itemGroup.name.chestplate"
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun menuCategory(data: CategoryData.() -> Unit) {
            menuCategoryData = (menuCategoryData ?: CategoryData()).apply(data)
        }

        class CategoryData : MonsteraRawFile() {
            @SerializedName("category")
            @Expose
            var category: Category? = null

            @SerializedName("group")
            @Expose
            var group: String? = null
        }

        enum class Category {
            @SerializedName("items")
            ITEMS,

            @SerializedName("construction")
            CONSTRUCTION,

            @SerializedName("equipment")
            EQUIPMENT;

            override fun toString(): String {
                return super.toString().lowercase()
            }
        }
    }
}