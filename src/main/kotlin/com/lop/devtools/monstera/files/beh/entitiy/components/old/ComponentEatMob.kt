package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentEatMob: MonsteraFile {
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

    var runSpeed: Number = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var eatAnimationTime: Number = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var pullInForce: Number = 0
        set(value) {
            unsafe.general["priority"] = value
            field = value
        }

    var reachMobDistance: Number = 0
        set(value) {
            unsafe.general["reach_mob_distance"] = value
            field = value
        }

    var eatMobSound: String = ""
        set(value) {
            unsafe.general["eat_mob_sound"] = value
            field = value
        }

    var lootTable: String = ""
        set(value) {
            unsafe.general["loot_table"] = value
            field = value
        }
}