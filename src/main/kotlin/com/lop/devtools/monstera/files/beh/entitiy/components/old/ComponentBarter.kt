package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBarter: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            priority?.let { general["priority"] = it }
            return general
        }
    }

    var priority: Int? = null

}

class ComponentBarterComp: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            barterTable?.let { general["barter_table"] = it }
            cooldownAfterBeingAttacked?.let { general["cooldown_after_being_attacked"] = it }
            return general
        }
    }

    var barterTable: String? = null
    var cooldownAfterBeingAttacked: Number? = null
}
