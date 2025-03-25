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
import com.lop.devtools.monstera.files.sanitiseFilename
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
            sanitiseFilename(filename, "json"),
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
        var menuCategory = ItemCategory.ITEMS
            set(value) {
                menuCategoryData = CategoryData().also { it.category = value }
                field = value
            }

        /**
         * ```
         * category = Category.EQUIPMENT
         * group = "itemGroup.name.chestplate"
         * isHiddenInCommands = true
         * ```
         */
        @OptIn(MonsteraBuildSetter::class)
        fun menuCategory(data: CategoryData.() -> Unit) {
            menuCategoryData = (menuCategoryData ?: CategoryData()).apply(data)
        }

        class CategoryData : MonsteraRawFile() {
            /**
             * ```
             * category = ItemCategory.EQUIPMENT
             * ```
             */
            @SerializedName("category")
            @Expose
            var category: String? = null

            /**
             * ```
             * group = ItemGroup.ANVIL
             * ```
             */
            @SerializedName("group")
            @Expose
            var group: String? = null

            @SerializedName("is_hidden_in_commands")
            @Expose
            var isHiddenInCommands: Boolean? = null
        }

        @Deprecated("use top level object", ReplaceWith("ItemCategory"))
        object Category {
            const val ITEMS = "items"
            const val CONSTRUCTION = "construction"
            const val EQUIPMENT = "equipment"
            const val NATURE = "nature"
        }
    }
}

object ItemCategory {
    const val ITEMS = "items"
    const val CONSTRUCTION = "construction"
    const val EQUIPMENT = "equipment"
    const val NATURE = "nature"
    const val NONE = "none"
}

object ItemGroup {
    const val ANVIL = "itemGroup.name.anvil"
    const val ARROW = "itemGroup.name.arrow"
    const val AXE = "itemGroup.name.axe"
    const val BANNER = "itemGroup.name.banner"
    const val BANNER_PATTERN = "itemGroup.name.banner_pattern"
    const val BED = "itemGroup.name.bed"
    const val BOAT = "itemGroup.name.boat"
    const val BOOTS = "itemGroup.name.boots"
    const val BUTTONS = "itemGroup.name.buttons"
    const val CANDLES = "itemGroup.name.candles"
    const val CHALKBOARD = "itemGroup.name.chalkboard"
    const val CHEST = "itemGroup.name.chest"
    const val CHESTBOAT = "itemGroup.name.chestboat"
    const val CHESTPLATE = "itemGroup.name.chestplate"
    const val CONCRETE = "itemGroup.name.concrete"
    const val CONCRETE_POWDER = "itemGroup.name.concretePowder"
    const val COOKED_FOOD = "itemGroup.name.cookedFood"
    const val COPPER = "itemGroup.name.copper"
    const val CORAL = "itemGroup.name.coral"
    const val CORAL_DECORATIONS = "itemGroup.name.coral_decorations"
    const val CROP = "itemGroup.name.crop"
    const val DOOR = "itemGroup.name.door"
    const val DYE = "itemGroup.name.dye"
    const val ENCHANTED_BOOK = "itemGroup.name.enchantedBook"
    const val FENCE = "itemGroup.name.fence"
    const val FENCE_GATE = "itemGroup.name.fenceGate"
    const val FIREWORK = "itemGroup.name.firework"
    const val FIREWORK_STARS = "itemGroup.name.fireworkStars"
    const val FLOWER = "itemGroup.name.flower"
    const val GLASS = "itemGroup.name.glass"
    const val GLASS_PANE = "itemGroup.name.glassPane"
    const val GLAZED_TERRACOTTA = "itemGroup.name.glazedTerracotta"
    const val GOAT_HORN = "itemGroup.name.goatHorn"
    const val GRASS = "itemGroup.name.grass"
    const val HANGING_SIGN = "itemGroup.name.hanging_sign"
    const val HELMET = "itemGroup.name.helmet"
    const val HOE = "itemGroup.name.hoe"
    const val HORSE_ARMOR = "itemGroup.name.horseArmor"
    const val LEAVES = "itemGroup.name.leaves"
    const val LEGGINGS = "itemGroup.name.leggings"
    const val LINGERING_POTION = "itemGroup.name.lingeringPotion"
    const val LOG = "itemGroup.name.log"
    const val MINECART = "itemGroup.name.minecart"
    const val MISC_FOOD = "itemGroup.name.miscFood"
    const val MOB_EGG = "itemGroup.name.mobEgg"
    const val MONSTER_STONE_EGG = "itemGroup.name.monsterStoneEgg"
    const val MUSHROOM = "itemGroup.name.mushroom"
    const val NETHER_WART_BLOCK = "itemGroup.name.netherWartBlock"
    const val ORE = "itemGroup.name.ore"
    const val PERMISSION = "itemGroup.name.permission"
    const val PICKAXE = "itemGroup.name.pickaxe"
    const val PLANKS = "itemGroup.name.planks"
    const val POTION = "itemGroup.name.potion"
    const val POTTERY_SHERDS = "itemGroup.name.potterySherds"
    const val PRESSURE_PLATE = "itemGroup.name.pressurePlate"
    const val RAIL = "itemGroup.name.rail"
    const val RAW_FOOD = "itemGroup.name.rawFood"
    const val RECORD = "itemGroup.name.record"
    const val SANDSTONE = "itemGroup.name.sandstone"
    const val SAPLING = "itemGroup.name.sapling"
    const val SCULK = "itemGroup.name.sculk"
    const val SEED = "itemGroup.name.seed"
    const val SHOVEL = "itemGroup.name.shovel"
    const val SHULKER_BOX = "itemGroup.name.shulkerBox"
    const val SIGN = "itemGroup.name.sign"
    const val SKULL = "itemGroup.name.skull"
    const val SLAB = "itemGroup.name.slab"
    const val SMITHING_TEMPLATES = "itemGroup.name.smithing_templates"
    const val SPLASH_POTION = "itemGroup.name.splashPotion"
    const val STAINED_CLAY = "itemGroup.name.stainedClay"
    const val STAIRS = "itemGroup.name.stairs"
    const val STONE = "itemGroup.name.stone"
    const val STONE_BRICK = "itemGroup.name.stoneBrick"
    const val SWORD = "itemGroup.name.sword"
    const val TRAPDOOR = "itemGroup.name.trapdoor"
    const val WALLS = "itemGroup.name.walls"
    const val WOOD = "itemGroup.name.wood"
    const val WOOL = "itemGroup.name.wool"
    const val WOOL_CARPET = "itemGroup.name.woolCarpet"
}