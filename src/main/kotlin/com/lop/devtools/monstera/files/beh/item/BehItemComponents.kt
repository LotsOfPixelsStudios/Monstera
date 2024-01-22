package com.lop.devtools.monstera.files.beh.item

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.item.comp.*
import com.lop.devtools.monstera.files.beh.item.comp.food.Saturation

class BehItemComponents : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()
        val repairItems = BehItemRepairable()

        fun icon(textureIndex: String) {
            general["minecraft:icon"] = mapOf(
                "texture" to textureIndex
            )
        }

        fun displayName(key: String) {
            general["minecraft:display_name"] = mapOf(
                "value" to key
            )
        }

        override fun getData(): MutableMap<String, Any> {
            if (repairItems.unsafe.getData().isNotEmpty())
                unsafe.general["minecraft:repairable"] = repairItems.unsafe.getData()
            return unsafe.general
        }
    }

    /**
     * chest-plate category:
     *
     * ```
     * creativeInventory("itemGroup.name.chestplate")
     * ```
     */
    fun creativeInventory(parent: String) {
        unsafe.general["minecraft:creative_category"] = mapOf(
            "parent" to parent
        )
    }

    var maxStackSize: Int = 0
        set(value) {
            unsafe.general["minecraft:max_stack_size"] = value
            field = value
        }

    /**
     * enchants for chest:
     *
     * ```
     * enchantable(10, "armor_torso")
     * ```
     */
    fun enchantable(value: Int, slot: String) {
        unsafe.general["minecraft:enchantable"] = mapOf(
            "value" to value,
            "slot" to slot
        )
    }

    fun armor(protection: Int) {
        unsafe.general["armor"] = mapOf(
            "protection" to protection
        )
    }

    /**
     * ```
     * repairable {
     *     items("context.other->q.remaining_durability + 0.05 * context.other->q.max_durability", "minecraft:stick")
     * }
     * ```
     */
    fun repairable(data: BehItemRepairable.() -> Unit) {
        unsafe.repairItems.apply(data)
    }

    /**
     * ```
     * wearable(true, "slot.armor.chest")
     * ```
     */
    fun wearable(dispensable: Boolean, slot: Slot) {
        unsafe.general["minecraft:wearable"] = mapOf(
            "dispensable" to dispensable,
            "slot" to slot.toString()
        )
    }

    /**
     * 0..1
     *
     * 	Durability item component: how much damage can this item take before breaking.
     * 	@param damageChance: Damage chance.
     * 	@param maxDurability: Max durability is the amount of damage that this item can take before breaking.
     */
    fun durability(damageChance: Float, maxDurability: Int) {
        unsafe.general["minecraft:durability"] = mapOf(
            "damage_chance" to damageChance,
            "max_durability" to maxDurability
        )
    }

    //-----------------------------------------------------------

    /**
     * 0..1
     */
    fun maxDamage(value: Int) {
        unsafe.general.apply {
            put("minecraft:max_damage", value)
        }
    }

    /**
     * 0..1
     */
    var handEquipped = false
        set(value) {
            unsafe.general["minecraft:hand_equipped"] = value
            field = value
        }

    /**
     * 0..1
     */
    fun stackedByData(value: Boolean) {
        unsafe.general.apply {
            put("minecraft:stacked_by_data", value)
        }
    }

    /**
     * 0..1
     */
    fun foil(value: Boolean) {
        unsafe.general.apply {
            put("minecraft:foil", value)
        }
    }

    /**
     * 0..1
     */
    fun block(value: String) {
        unsafe.general.apply {
            put("minecraft:block", value)
        }
    }

    /**
     * 0..1
     */
    fun useDuration(value: Int = 32) {
        unsafe.general.apply {
            put("minecraft:use_duration", value)
        }
    }

    /**
     * 0..1
     *
     * @sample sampleFood
     */
    fun food(settings: BehItemFood.() -> Unit) {
        val behItemFood = BehItemFood().apply { settings(this) }
        unsafe.general.apply {
            put("minecraft:food", behItemFood.getData())
        }
    }

    /**
     * 0..1
     *
     * @sample sampleSeed
     */
    @Deprecated("use blockPlacer() instead, if in 1.17 or higher")
    fun seed(settings: BehItemSeed.() -> Unit) {
        val behItemSeed = BehItemSeed().apply { settings(this) }
        unsafe.general.apply {
            put("minecraft:seed", behItemSeed.getData())
        }
    }

    //-----------------------------------------------------------

    /**
     * 0..1
     *
     * The armor item componenent determines the amount of protection you have in your armor item.
     * @param protection How much protection does the armor item have.
     * @param textureType Texture Type to apply for the armor. Note that Horse armor is restricted to leather, iron, gold, or diamond.
     */
    @ExperimentalUnsignedTypes
    fun armor(protection: Int, textureType: String) {
        unsafe.general.apply {
            put(
                "minecraft:armor",
                mutableMapOf(
                    "protection" to protection,
                    "texture_type" to textureType
                )
            )
        }
    }

    /**
     * 0..1
     *
     *  Replaces seed() ?!
     *
     * 	Planter item component. planter items are items that can be planted.
     * 	@param resultBlock block: Set the placement block name for the planter item.
     * 	@param useOn List of block descriptors that contain blocks that this item can be used on. If left empty, all blocks will be allowed.
     */
    @ExperimentalUnsignedTypes
    fun blockPlacer(resultBlock: String, useOn: ArrayList<String>) {
        unsafe.general.apply {
            put(
                "minecraft:block_placer",
                mutableMapOf(
                    "block" to resultBlock,
                    "use_on" to useOn
                )
            )
        }
    }


    /**
     * 0..1
     *
     * 	Cool down time for a component.
     * 	After you use an item it becomes unusable for the duration specified by the 'cool down time' setting in this component.
     *
     * 	@param category The type of cool down for this item.
     * 	@param duration The duration of time this item will spend cooling down before becoming usable again.
     */
    @ExperimentalUnsignedTypes
    fun coolDown(category: String, duration: Float) {
        unsafe.general.apply {
            put(
                "minecraft:cooldown",
                mutableMapOf(
                    "category" to category,
                    "duration" to duration
                )
            )
        }
    }

    /**
     * 0..1
     *
     * 	Digger item. Component put on items that dig.
     *
     * 	@param destroySpeeds Destroy speed per block.
     * 	@param onDig Trigger for when you dig a block that isn't listed in destroy_speeds
     * 	@param useEfficiency Use efficiency? Default is set to false.
     */
    @ExperimentalUnsignedTypes
    fun digger(destroySpeeds: String, onDig: String, useEfficiency: Boolean) {
        unsafe.general.apply {
            put(
                "minecraft:digger",
                mutableMapOf(
                    "destroy_speeds" to destroySpeeds,
                    "on_dig" to onDig,
                    "use_efficiency" to useEfficiency
                )
            )
        }
    }


    /**
     * 0..1
     *
     * Dye powder, there are 16 kinds of dye.
     * @param color: Defines what color the dye is.
     */
    @ExperimentalUnsignedTypes
    fun dyePowder(color: String) {
        unsafe.general.apply {
            put("minecraft:dye_powder", color)
        }
    }

    /**
     * 0..1
     *
     * Entity placer item component. You can specifiy allowed blocks that the item is restricted to.
     * @param dispenseOn List of block descriptors that contain blocks that this item can be dispensed on. If left empty, all blocks will be allowed.
     * @param entity The entity to be placed in the world.
     * @param useOn List of block descriptors that contain blocks that this item can be used on. If left empty, all blocks will be allowed.
     */
    @ExperimentalUnsignedTypes
    fun entityPlacer(dispenseOn: ArrayList<String>, entity: String, useOn: String) {
        unsafe.general.apply {
            put(
                "minecraft:entity_placer", mutableMapOf(
                    "dispense_on" to dispenseOn,
                    "entity" to entity,
                    "useOn" to useOn
                )
            )
        }
    }

    /**
     * 0..1
     *
     * Entity placer item component. You can specifiy allowed blocks that the item is restricted to.
     * @param dispenseOn List of block descriptors that contain blocks that this item can be dispensed on. If left empty, all blocks will be allowed.
     * @param entity The entity to be placed in the world.
     */
    @ExperimentalUnsignedTypes
    fun entityPlacer(dispenseOn: ArrayList<String>, entity: String) {
        unsafe.general.apply {
            put(
                "minecraft:entity_placer", mutableMapOf(
                    "dispense_on" to dispenseOn,
                    "entity" to entity
                )
            )
        }
    }

    /**
     * 0..1
     *
     * Fuel component. Allows this item to be used as fuel in a furnace to 'cook' other items.
     * @param duration How long in seconds will this fuel cook items for.
     */
    @ExperimentalUnsignedTypes
    fun fuel(duration: Float) {
        unsafe.general.apply {
            put("minecraft:fuel", duration)
        }
    }

    /**
     * 0..1
     *
     * 	Knockback Resistance Item. Component put on items that provide knockback resistance.
     * 	@param protection Amount of knockback resistance provided with the total maximum protection being 1.0
     */
    @ExperimentalUnsignedTypes
    fun knockBackResistance(protection: Float) {
        unsafe.general.apply {
            put("minecraft:knockback_resistance", protection)
        }
    }

    /**
     * 0..1
     *
     * 	The on_use item component allows you to receive an event when the item is used.
     * 	@param onUse Event trigger for when the item is used.
     */
    @ExperimentalUnsignedTypes
    fun onUse(onUse: String) {
        unsafe.general.apply {
            put("minecraft:on_use", onUse)
        }
    }

    /**
     * 0..1
     *
     * The on_use_on item component allows you to receive an event when the item is used on a block in the world.
     * @param onUseOn Event trigger for when the item is used.
     */
    @ExperimentalUnsignedTypes
    fun onUseOn(onUseOn: String) {
        unsafe.general.apply {
            put("minecraft:on_use_on", onUseOn)
        }
    }

    /**
     * 0..1
     *
     * 	Projectile item component. projectile items shoot out, like an arrow.
     * 	@param minCriticalPower How long you must charge a projectile for it to critically hit.
     * 	@param projectileEntity The entity to be fired as a projectile.
     */
    @ExperimentalUnsignedTypes
    fun projectile(minCriticalPower: Float, projectileEntity: String) {
        unsafe.general.apply {
            put(
                "minecraft:projectile", mutableMapOf(
                    "minimum_critical_power" to minCriticalPower,
                    "projectile_entity" to projectileEntity
                )
            )
        }
    }


    /**
     * 	Shooter Item Component.
     *
     * 	@sample sampleShooter
     */
    @ExperimentalUnsignedTypes
    fun shooter(settings: BehItemShooter.() -> Unit) {
        val behItemShooter = BehItemShooter().apply { settings(this) }
        unsafe.general.apply {
            put("minecraft:shooter", behItemShooter.unsafe.getData())
        }
    }

    /**
     * 	Weapon Item Component. Added to every weapon item such as axe, sword, trident, bow, crossbow.
     *
     * 	@sample sampleWeapon
     */
    @ExperimentalUnsignedTypes
    fun weapon(settings: BehItemWeapon.() -> Unit) {
        val behItemWeapon = BehItemWeapon().apply { settings(this) }
        unsafe.general.apply {
            put("minecraft:weapon", behItemWeapon.unsafe.getData())
        }
    }

    private fun sampleFood() {
        food {
            nutrition(3)
            saturation(Saturation.low)
            useConvertTo("stick")
            alwaysEat(false)
            effects {
                effect(
                    "strength",
                    1.0f,
                    30,
                    3
                )
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun sampleSeed() {
        seed {
            cropResult("grass")
            plantAt(
                arrayListOf(
                    "stone", "dirt"
                )
            )
        }
    }

    @ExperimentalUnsignedTypes
    private fun sampleShooter() {
        shooter {
            ammunition {
                item = "minecraft:arrow"
                useOffhand = true
                searchInventory = true
                useInCreative = true
            }
            ammunition {
                item = "minecraft:fireworks_rocket"
                useOffhand = true
                searchInventory = true
                useInCreative = true
            }
            chargeOnDraw(true)
            launchPowerScale(1.0f)
            maxDrawDuration(2)
            maxLaunchPower(1.0f)
            scalePowerByDrawDuration(true)
        }
    }

    @ExperimentalUnsignedTypes
    private fun sampleWeapon() {
        weapon {
            onHitBlock("event")
            onHurtEntity("event")
            onNotHurtEntity("event")
        }
    }
}