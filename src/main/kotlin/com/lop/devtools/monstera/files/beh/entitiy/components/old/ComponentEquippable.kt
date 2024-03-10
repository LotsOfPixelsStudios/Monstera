package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentEquippable {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     *
     * @param acceptedItems The list of items that can go in this slot.
     * @param interactText Text to be displayed when the entity can be equipped with this item when playing with Touch-screen controls.
     * @param item Identifier of the item that can be equipped for this slot.
     * @param onEquip Event to trigger when this entity is equipped with this item.
     * @param onUnequip Event to trigger when this item is removed from this entity.
     * @param slot The slot number of this slot.
     */
    fun slot(
        acceptedItems: ArrayList<String>? = null,
        interactText: String? = null,
        item: String? = null,
        onEquip: String? = null,
        onUnequip: String? = null,
        slot: Int = 0
    ) {
        val thisData = mutableMapOf<Any, Any>()
        if (acceptedItems != null) {
            thisData.apply { put("accepted_items", acceptedItems) }
        }
        if (interactText != null) {
            thisData.apply { put("interact_text", interactText) }
        }
        if (item != null) {
            thisData.apply { put("item", item) }
        }
        if (onEquip != null) {
            thisData.apply { put("on_equip", mutableMapOf("event" to onEquip)) }
        }
        if (onUnequip != null) {
            thisData.apply { put("on_unequip", mutableMapOf("event" to onUnequip)) }
        }
        thisData.apply { put("slot", slot) }

        general.add(thisData)
    }

    fun slot(data: CompEqSlot.() -> Unit) {
        general.add(CompEqSlot().apply(data).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class CompEqSlot {
    var general = mutableMapOf<String, Any>()

    var acceptedItems: ArrayList<String>? = null
    var interactText: String? = null
    var item: String? = null
    var onEquip: String? = null
    var onUnequip: String? = null
    var slot: Int? = null

    fun getData(): MutableMap<String, Any> {
        acceptedItems?.let { general["accepted_items"] = it }
        interactText?.let { general["interact_text"] = it }
        item?.let { general["item"] = it }
        onEquip?.let { general["on_equip"] = mutableMapOf("event" to it) }
        onUnequip?.let { general["on_unequip"] = mutableMapOf("event" to it) }
        slot?.let { general["slot"] = it }
        return general
    }
}