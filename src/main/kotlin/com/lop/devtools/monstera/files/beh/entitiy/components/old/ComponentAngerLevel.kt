package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentAngerLevel : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val onIncreaseSounds = mutableListOf<Pair<String, String>>()

        override fun getData(): MutableMap<String, Any> {
            if (onIncreaseSounds.isNotEmpty()) {
                general["on_increase_sounds"] = onIncreaseSounds.map {
                    mapOf("sound" to it.first, "condition" to it.second)
                }
            }
            return general
        }
    }

    var maxAnger: Number = 0
        set(value) {
            unsafe.general["max_anger"] = value
            field = value
        }

    var angryThreshold: Number = 0
        set(value) {
            unsafe.general["angry_threshold"] = value
            field = value
        }
    var removeTargetsBelowAngryThreshold: Boolean = false
        set(value) {
            unsafe.general["remove_targets_below_angry_threshold"] = value
            field = value
        }
    var angryBoost: Number = 0
        set(value) {
            unsafe.general["angry_boost"] = value
            field = value
        }
    var angerDecrementInterval: Number = 0
        set(value) {
            unsafe.general["anger_decrement_interval"] = value
            field = value
        }

    var defaultAnnoyingness: Number = 0
        set(value) {
            unsafe.general["default_annoyingness"] = value
            field = value
        }

    var defaultProjectileAnnoyingness: Number = 0
        set(value) {
            unsafe.general["default_projectile_annoyingness"] = value
            field = value
        }

    fun onIncreaseSound(vararg soundConditionPair: Pair<String, Molang>) {
        unsafe.onIncreaseSounds.addAll(soundConditionPair.map { it.first to it.second.data })
    }

    fun onIncreaseSound(sound: String, condition: Molang) {
        onIncreaseSound(sound, condition.data)
    }

    fun onIncreaseSound(sound: String, condition: String) {
        unsafe.onIncreaseSounds.add(sound to condition)
    }

    fun nuisanceFilter(filter: BehEntityFilter.() -> Unit) {
        unsafe.general["nuisance_filter"] = BehEntityFilter().apply(filter).getData()
    }
}