package com.lop.devtools.monstera.files.beh.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.getMonsteraLogger
import java.nio.file.Path

class BehItem : MonsteraBuildableFile {

    override fun build(filename: String, path: Path?, version: String?) {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        val selPath = path ?: Addon.active?.config?.paths?.behItems ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for item file '$sanFile' as no addon was initialized!")
            return
        }

        MonsteraBuilder.buildTo(
            selPath,
            "$sanFile.json",
            FileHeader(
                version ?: Addon.active?.config?.formatVersions?.behItem ?: "1.50.0",
                this
            )
        )
    }

    /**
     * load json blocks with this class
     */
    data class FileHeader(
        @SerializedName("format_version")
        @Expose
        var formatVersion: String = "",

        @SerializedName("minecraft:block")
        @Expose
        var block: BehItem
    )

    @SerializedName("description")
    @Expose
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

    class Description {
        @SerializedName("identifier")
        @Expose
        var identifier: String? = null

        @SerializedName("menu_category")
        @Expose
        var menuCategoryData: CategoryData? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        var menuCategory = Category.ITEMS
            set(value) {
                menuCategoryData = CategoryData().also { it.category = value }
                field = value
            }

        class CategoryData {
            @SerializedName("category")
            @Expose
            var category: Category? = null
        }

        enum class Category {
            ITEMS,
            CONSTRUCTION;

            override fun toString(): String {
                return super.toString().lowercase()
            }
        }
    }
}