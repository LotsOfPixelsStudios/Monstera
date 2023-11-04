package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentHealable {
    val general = mutableMapOf<String, Any>()

    var forceUse: Boolean? = null

    /**
     * 0..1
     *
     * The filter group that defines the conditions for using this item to heal the entity.
     */
    fun filters(filters: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filters).getData()
    }

    /**
     * call item()
     */
    fun items(settings: ComponentHealItems.() -> Unit) {
        val componentHealItems = ComponentHealItems().apply { settings(this) }
        general.apply {
            put("items", componentHealItems.getData())
        }
    }

    fun getData(): MutableMap<String, Any> {
        forceUse?.let { general["force_use"] = it }
        return general
    }
}

class ComponentHealItems {
    val general = arrayListOf<Any>()

    fun item(healAmount: Int = 1, item: String) {
        general.add(
            mutableMapOf(
                "heal_amount" to healAmount,
                "item" to item
            )
        )
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}