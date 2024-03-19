package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentVariableMaxAutoStepMonsteraFile : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData() = general
    }

    var baseValue: Number = 0
        set(value) {
            unsafe.general["base_value"] = value
            field = value
        }

    var controlledValue: Number = 0
        set(value) {
            unsafe.general["controlled_value"] = value
            field = value
        }

    var jumpPreventedValue: Number = 0
        set(value) {
            unsafe.general["jump_prevented_value"] = value
            field = value
        }
}