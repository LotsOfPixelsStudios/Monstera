package com.lop.devtools.monstera.files.beh.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.item.Item
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.item.comp.*

class BehItemComponents : MonsteraRawFile() {
    @SerializedName("minecraft:icon")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var itemIconData: ItemIcon? = null
        @MonsteraBuildSetter set

    /**
     * icon {
     *     texture = "custom"
     * }
     */
    @OptIn(MonsteraBuildSetter::class)
    fun icon(data: ItemIcon.() -> Unit) {
        itemIconData = (itemIconData ?: ItemIcon()).apply(data)
    }

    @SerializedName("minecraft:allow_off_hand")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var allowOffHandData: ItemAllowOffHand? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun allowOffHand(value: Boolean) {
        allowOffHandData = ItemAllowOffHand().apply { this.value = value }
    }

    @SerializedName("minecraft:armor")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var armorData: ItemArmor? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * protection = 5
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Deprecated("Deprecated since 1.50.0", ReplaceWith("wearable { }"))
    fun armor(data: ItemArmor.() -> Unit) {
        armorData = (armorData ?: ItemArmor()).apply(data)
    }

    @SerializedName("minecraft:block_placer")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var blockPlaceData: ItemBlockPlacer? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * blockPlacer {
     *    block = "seeds"
     *    useOn = mutableListOf(
     *        "dirt",
     *        "grass"
     *    )
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun blockPlacer(data: ItemBlockPlacer.() -> Unit) {
        blockPlaceData = (blockPlaceData ?: ItemBlockPlacer()).apply(data)
    }

    @SerializedName("minecraft:can_destroy_in_creative")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var canDestroyInCreativeData: ItemCanDestroyInCreative? = null
        @MonsteraBuildSetter set

    /**
     * Determines if an item will break blocks in Creative Mode while swinging.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun canDestroyInCreative(value: Boolean = true) {
        canDestroyInCreativeData = ItemCanDestroyInCreative().apply { this.value = value }
    }

    @SerializedName("minecraft:cooldown")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var cooldownData: ItemCooldown? = null
        @MonsteraBuildSetter set

    /**
     * Sets an items "Cool down" time. After using an item, it becomes unusable for the duration specified by the
     * 'duration' setting of this component.
     *
     * ```
     * cooldown {
     *      category = "attack"
     *      duration = 0.2
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun cooldown(data: ItemCooldown.() -> Unit) {
        cooldownData = (cooldownData ?: ItemCooldown()).apply(data)
    }

    @SerializedName("minecraft:creative_category")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var creativeCategoryData: ItemCreativeCategory? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * parent = ""
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun creativeCategory(data: ItemCreativeCategory.() -> Unit) {
        creativeCategoryData = (creativeCategoryData ?: ItemCreativeCategory()).apply(data)
    }

    /**
     * Sets an items "Cool down" time. After using an item, it becomes unusable for the duration specified by the
     * 'duration' setting of this component.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun cooldown(duration: Number, category: String = "attack") {
        cooldownData = ItemCooldown().apply {
            this.category = category
            this.duration = duration
        }
    }

    @SerializedName("minecraft:damage")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var damageData: ItemDamage? = null
        @MonsteraBuildSetter set

    /**
     * Determines how much extra damage an item does on attack.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun damage(value: Number) {
        damageData = ItemDamage().apply { this.value = value }
    }

    @SerializedName("minecraft:digger")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var diggerData: ItemDigger? = null
        @MonsteraBuildSetter set

    /**
     * Allows a creator to determine how quickly an item can dig specific blocks.
     *
     * ```
     * digger {
     *     useEfficiency = true
     *     destroySpeed {
     *         speed = 6
     *         block("minecraft:stone")
     *     }
     *     destroySpeed { ... }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun digger(data: ItemDigger.() -> Unit) {
        diggerData = (diggerData ?: ItemDigger()).apply(data)
    }

    @SerializedName("minecraft:display_name")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var displayNameData: ItemDisplayName? = null
        @MonsteraBuildSetter set


    /**
     * sets the display name of the item, can be a lang key or a normal name
     *
     * note: this function will not add the lang key to the lang file builder
     */
    @OptIn(MonsteraBuildSetter::class)
    fun displayNameKey(key: String) {
        displayNameData = ItemDisplayName().apply { value = key }
    }

    /**
     * Sets the display name of the item, note this can also be set with the displayName variable on the [Item] object.
     *
     * The name will be added as a lang key to the lang files.
     * @param name the name of the item, this may also contain color or markup information
     * @param langHint if the target language shouldn't be the standard one defined in the addon (default en_US)
     * @return the lang key generated
     */
    fun Item.displayName(name: String, langHint: String? = null): String {
        val key = "item.${identifier()}.name"
        addon.config.langFileBuilder.addonRes.add(key, name, langHint)
        displayNameKey(key)
        return key
    }

    @SerializedName("minecraft:durability")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var durabilityData: ItemDurability? = null
        @MonsteraBuildSetter set

    /**
     * Sets how much damage the item can take before breaking, and allows the item to be combined at an anvil, grindstone, or crafting table.
     *
     * ```
     * durability {
     *     damageChance(min = 10, max = 50)
     *     maxDurability = 36
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun durability(data: ItemDurability.() -> Unit) {
        durabilityData = (durabilityData ?: ItemDurability()).apply(data)
    }

    @SerializedName("minecraft:enchantable")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var enchantableData: ItemEnchantable? = null
        @MonsteraBuildSetter set

    /**
     * Determines what enchantments can be applied to the item. Not all enchantments will have an effect on all item components.
     *
     * ```
     * enchantable {
     *     slot = EnchantableSlot.BOW
     *     value = 10
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun enchantable(data: ItemEnchantable.() -> Unit) {
        enchantableData = (enchantableData ?: ItemEnchantable().apply(data))
    }

    @SerializedName("minecraft:entity_placer")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var entityPlacerData: ItemEntityPlacer? = null
        @MonsteraBuildSetter set

    /**
     * Allows an item to place entities into the world. Additionally, in version 1.19.80 and above, the component allows the item to set the spawn type of a monster spawner.
     *
     * ```
     * entityPlacer {
     *     entity = "minecraft:spider"
     *     dispenseOn = mutableListOf("minecraft:web")
     *     useOn = mutableListOf("minecraft:web")
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun entityPlacer(data: ItemEntityPlacer.() -> Unit) {
        entityPlacerData = (entityPlacerData ?: ItemEntityPlacer().apply(data))
    }

    @SerializedName("minecraft:food")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var foodData: ItemFood? = null
        @MonsteraBuildSetter set

    /**
     * Sets the item as a food component, allowing it to be edible to the player.
     *
     * *The minecraft:food must have the minecraft:use_modifiers component in order to function properly.*
     *
     * ```
     * food {
     *     canAlwaysEat = true
     *     nutrition = 3
     *     effect {
     *         name = "poison"
     *         chance = 1
     *         duration = 5
     *         amplifier = 0
     *     }
     *     effect { ... }
     *     saturationModifier = Saturation.NORMAL
     *     usingConvertsTo = "bowl"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun food(data: ItemFood.() -> Unit) {
        foodData = (foodData ?: ItemFood().apply(data))
    }

    @SerializedName("minecraft:fuel")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var fuelData: ItemFuel? = null
        @MonsteraBuildSetter set

    /**
     * Allows this item to be used as fuel in a furnace to 'cook' other items.
     *
     * ```
     * fuel {
     *     duration = 3
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun fuel(data: ItemFuel.() -> Unit) {
        fuelData = (fuelData ?: ItemFuel().apply(data))
    }

    /**
     * Determines whether the item has the enchanted glint render effect on it.
     */
    @SerializedName("minecraft:glint")
    @Expose
    var glint: Boolean? = null

    @SerializedName("minecraft:hand_equipped")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var handEquippedData: ItemHandEquipped? = null
        @MonsteraBuildSetter set

    /**
     * Determines if an item is rendered like a tool while in-hand.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun handEquipped(value: Boolean = true) {
        handEquippedData = ItemHandEquipped().apply { this.value = value }
    }

    /**
     * Determines the color of the item name when hovering over it.
     */
    @SerializedName("minecraft:hover_text_color")
    @Expose
    var hoverTextColor: String? = null

    /**
     * Is a boolean or string that determines if the interact button is shown in touch controls, and what text is
     * displayed on the button. When set to 'true', the default 'Use Item' text will be used.
     */
    @SerializedName("minecraft:interact_button")
    @Expose
    var interactButton: Any? = null

    @SerializedName("minecraft:liquid_clipped")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var liquidClippedData: ItemLiquidClipped? = null
        @MonsteraBuildSetter set


    /**
     * Determines whether an item interacts with liquid blocks on use.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun liquidClipped(value: Boolean = true) {
        liquidClippedData = ItemLiquidClipped().apply { this.value = value }
    }

    @SerializedName("minecraft:max_stack_size")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var maxStackSizeData: ItemMaxStackSize? = null
        @MonsteraBuildSetter set

    /**
     * Determines how many of an item can be stacked together.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun maxStackSize(value: Int = 64) {
        maxStackSizeData = ItemMaxStackSize().apply { this.value = value }
    }

    @SerializedName("minecraft:projectile")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var projectileData: ItemProjectile? = null
        @MonsteraBuildSetter set

    /**
     * Compels the item to shoot, similarly to an arrow. Items with `minecraft:projectile` can be shot from dispensers
     * or used as ammunition for items with the `minecraft:shooter` item component. Additionally, this component sets the
     * entity that is spawned for items that also contain the `minecraft:throwable` component.
     *
     * ```
     * projectile {
     *      minimumCriticalPower = 1.25
     *      projectileEntity = "arrow"
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun projectile(data: ItemProjectile.() -> Unit) {
        projectileData = (projectileData ?: ItemProjectile()).apply(data)
    }

    @SerializedName("minecraft:record")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var recordData: ItemRecord? = null
        @MonsteraBuildSetter set

    /**
     * Used by record items to play music.
     *
     * ```
     * record {
     *     comparatorSignal = 1
     *     duration = 5
     *     soundEvent = "ambient.tame"
     * }
     * ```
     *
     * @see <a href="https://learn.microsoft.com/en-us/minecraft/creator/reference/content/itemreference/examples/itemcomponents/minecraft_record?view=minecraft-bedrock-stable">sound events</a>
     *
     */
    @OptIn(MonsteraBuildSetter::class)
    fun record(data: ItemRecord.() -> Unit) {
        recordData = (recordData ?: ItemRecord()).apply(data)
    }

    @SerializedName("minecraft:repairable")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var repairableData: ItemRepairable? = null
        @MonsteraBuildSetter set

    /**
     * Defines the items that can be used to repair a defined item, and the amount of durability each item restores
     * upon repair. Each entry needs to define a list of strings for 'items' that can be used for the repair and an
     * optional 'repair_amount' for how much durability is repaired.
     *
     * ```
     * repairable {
     *     onRepaired = "minecraft:celebrate"
     *     repairItems = mutableListOf("anvil")
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun repairable(data: ItemRepairable.() -> Unit) {
        repairableData = (repairableData ?: ItemRepairable()).apply(data)
    }

    @SerializedName("minecraft:shooter")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var shooterData: ItemShooter? = null
        @MonsteraBuildSetter set

    /**
     * Compels an item to shoot projectiles, similarly to a bow or crossbow. Must have the minecraft:use_modifiers
     * component in order to function properly.
     *
     * *Ammunition used by minecraft:shooter must have the minecraft:projectile component in order to function properly.*
     *
     * ```
     * shooter {
     *     maxDrawDuration = 1
     *     scalePowerByDrawDuration = true
     *     chargeOnDraw = false
     *     ammunition {
     *         item = "custom_projectile"
     *         useOffhand = true
     *         searchInventory = true
     *         useInCreative = true
     *     }
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun shooter(data: ItemShooter.() -> Unit) {
        shooterData = (shooterData ?: ItemShooter()).apply(data)
    }

    @SerializedName("minecraft:should_despawn")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var shouldDespawnData: ItemShouldDespawn? = null
        @MonsteraBuildSetter set

    /**
     * Determines if an item should despawn while floating in the world.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun shouldDespawn(value: Boolean = true) {
        shouldDespawnData = ItemShouldDespawn().apply { this.value = value }
    }

    @SerializedName("minecraft:stacked_by_data")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var stackedByDataData: ItemStackedByData? = null
        @MonsteraBuildSetter set

    /**
     * Determines if the same item with different aux values can stack. Additionally, this component defines whether the
     * item actors can merge while floating in the world.
     */
    @OptIn(MonsteraBuildSetter::class)
    fun stackedByData(value: Boolean = true) {
        stackedByDataData = ItemStackedByData().apply { this.value = value }
    }

    @SerializedName("minecraft:throwable")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var throwableData: ItemThrowable? = null
        @MonsteraBuildSetter set

    /**
     * Sets the throwable item component.
     *
     * ```
     * doSwingAnimation = true
     * launchPowerScale = 1.2
     * maxDrawDuration = 0.9
     * maxLaunchPower = 2
     * minDrawDuration = 0.2
     * scalePowerByDrawDuration = true
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun throwable(data: ItemThrowable.() -> Unit) {
        throwableData = (throwableData ?: ItemThrowable()).apply(data)
    }

    /**
     * Determines which animation plays when using an item.
     */
    @SerializedName("minecraft:use_animation")
    @Expose
    var useAnimation: String? = null

    @SerializedName("minecraft:use_modifiers")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var useModifiersData: ItemUseModifiers? = null
        @MonsteraBuildSetter set

    /**
     * Determines how long an item takes to use in combination with components such as Shooter, Throwable, or Food.
     *
     * ```
     * useModifiers {
     *     useDuration = 1.6
     *     movementModifier = 0.35
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun useModifiers(data: ItemUseModifiers.() -> Unit) {
        useModifiersData = (useModifiersData ?: ItemUseModifiers()).apply(data)
    }

    @SerializedName("minecraft:wearable")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var wearableData: ItemWearable? = null
        @MonsteraBuildSetter set

    /**
     * Sets the wearable item component.
     *
     * ```
     * wearable {
     *     dispensable = true
     *     slot = Slot.ARMOR_CHEST
     *     protection = 10
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun wearable(data: ItemWearable.() -> Unit) {
        wearableData = (wearableData ?: ItemWearable()).apply(data)
    }

    @SerializedName("tags")
    @Expose
    var tags: MutableList<String>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun tags(vararg tag: String) {
        tags = (tags ?: mutableListOf()).apply {
            addAll(tag)
        }
    }
}