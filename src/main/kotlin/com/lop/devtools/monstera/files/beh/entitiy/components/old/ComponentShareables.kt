package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentShareables {
    val general = mutableMapOf<String, Any>()

    var allItems: Boolean? = null
    var allItemsMaxAmount: Int? = null
    var allItemsSurplusAmount: Int? = null
    var allItemsWantAmount: Int? = null

    fun items(value: CompShareItems.() -> Unit) {
        general["items"] = CompShareItems().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        allItems?.let { general["all_items"] = it }
        allItemsMaxAmount?.let { general["all_items_max_amount"] = it }
        allItemsSurplusAmount?.let { general["all_items_surplus_amount"] = it }
        allItemsWantAmount?.let { general["all_items_want_amount"] = it }
        return general
    }
}

class CompShareItems {
    val general = arrayListOf<Any>()

    fun item(value: CompShareItem.() -> Unit) {
        general.add(CompShareItem().apply(value).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class CompShareItem {
    val general = mutableMapOf<String, Any>()

    var itemId: String? = null
    var admire: Boolean? = null
    var barter: Boolean? = null
    var consumeItem: Boolean? = null
    var craftInto: String? = null
    var maxAmount: Int? = null
    var pickupLimit: Int? = null
    var priority: Int? = null
    var storeInInventory: Boolean? = null
    var surplusAmount: Int? = null
    var wantAmount: Int? = null

    fun getData(): MutableMap<String, Any> {
        itemId?.let { general["item"] = it }
        admire?.let { general["admire"] = it }
        barter?.let { general["barter"] = it }
        consumeItem?.let { general["consume_item"] = it }
        craftInto?.let { general["craft_into"] = it }
        maxAmount?.let { general["max_amount"] = it }
        pickupLimit?.let { general["pickup_limit"] = it }
        priority?.let { general["priority"] = it }
        storeInInventory?.let { general["stored_in_inventory"] = it }
        surplusAmount?.let { general["surplus_amount"] = it }
        wantAmount?.let { general["want_amount"] = it }
        return general
    }
}