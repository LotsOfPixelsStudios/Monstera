package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentExhaustionValues : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var heal: Number = 0
        set(value) {
            unsafe.general["heal"] = value
            field = value
        }
    var jump: Number = 0
        set(value) {
            unsafe.general["jump"] = value
            field = value
        }
    var sprintJump: Number = 0
        set(value) {
            unsafe.general["sprint_jump"] = value
            field = value
        }
    var mine: Number = 0
        set(value) {
            unsafe.general["mine"] = value
            field = value
        }
    var attack: Number = 0
        set(value) {
            unsafe.general["attack"] = value
            field = value
        }
    var damage: Number = 0
        set(value) {
            unsafe.general["damage"] = value
            field = value
        }
    var walk: Number = 0
        set(value) {
            unsafe.general["walk"] = value
            field = value
        }
    var swim: Number = 0
        set(value) {
            unsafe.general["swim"] = value
            field = value
        }
}