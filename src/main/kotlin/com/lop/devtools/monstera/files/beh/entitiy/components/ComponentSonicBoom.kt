package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentSonicBoom: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var priority: Int = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var duration: Number = 0
        set(value) {
            unsafe.general["duration"] = value
            field = value
        }

    var speedMultiplier: Number = 0
        set(value) {
            unsafe.general["speed_multiplier"] = value
            field = value
        }

    var attackDamage: Number = 0
        set(value) {
            unsafe.general["attack_damage"] = value
            field = value
        }

    var attackRangeHorizontal: Number = 0
        set(value) {
            unsafe.general["attack_range_horizontal"] = value
            field = value
        }


    var attackRangeVertical: Number = 0
        set(value) {
            unsafe.general["attack_range_vertical"] = value
            field = value
        }

    var attackCooldown: Number = 0
        set(value) {
            unsafe.general["attack_cooldown"] = value
            field = value
        }

    var knockbackVerticalStrength: Number = 0
        set(value) {
            unsafe.general["knockback_vertical_strength"] = value
            field = value
        }

    var knockbackHorizontalStrength: Number = 0
        set(value) {
            unsafe.general["knockback_horizontal_strength"] = value
            field = value
        }

    var knockbackHeightCap: Number = 0
        set(value) {
            unsafe.general["knockback_height_cap"] = value
            field = value
        }

    var durationUntilAttackSound: Number = 0
        set(value) {
            unsafe.general["duration_until_attack_sound"] = value
            field = value
        }

    var chargeSound: String = ""
        set(value) {
            unsafe.general["charge_sound"] = value
            field = value
        }

    var attackSound: String = ""
        set(value) {
            unsafe.general["attack_sound"] = value
            field = value
        }
}