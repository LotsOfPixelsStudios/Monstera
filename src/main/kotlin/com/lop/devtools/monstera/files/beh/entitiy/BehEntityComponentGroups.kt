package com.lop.devtools.monstera.files.beh.entitiy

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehEntityComponentGroups : MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val componentGroups = mutableMapOf<String, BehEntityComponents>()

        override fun getData(): MutableMap<String, Any> = componentGroups.map {
            it.key to it.value.unsafe.getData()
        }.toMap().toMutableMap()
    }

    /**
     * 1..n instance allowed, 0 if componentGroups() in [BehEntity] isn't called
     *
     * @see BehEntityComponents
     */
    fun componentGroup(
        name: String,
        settings: BehEntityComponents.() -> Unit
    ) {
        unsafe.componentGroups[name]?.apply(settings) ?: run {
            unsafe.componentGroups[name] = BehEntityComponents().apply(settings)
        }
    }
}