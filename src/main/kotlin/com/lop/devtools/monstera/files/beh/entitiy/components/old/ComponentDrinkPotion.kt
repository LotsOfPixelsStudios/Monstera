package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentDrinkPotion {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedModifier: Float? = null

    /**
     * 0..1
     */
    fun potions(value: CompDrinkPots.() -> Unit) {
        general["potions"] = CompDrinkPots().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedModifier?.let { general["speed_modifier"] = it }

        return general
    }
}

class CompDrinkPots {
    val general = arrayListOf<MutableMap<String, Any>>()

    fun potion(entry: PotionEntry.() -> Unit) {
        general.add(PotionEntry().apply(entry).getData())
    }

    fun getData(): ArrayList<MutableMap<String, Any>> {
        return general
    }
}

class PotionEntry {
    val general = mutableMapOf<String, Any>()

    var chance: Float? = null
    var id: Int? = null

    fun filters(filers: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(filers).getData()
    }

    fun getData(): MutableMap<String, Any> {
        chance?.let { general["chance"] = it }
        id?.let { general["id"] = it }
        return general
    }
}