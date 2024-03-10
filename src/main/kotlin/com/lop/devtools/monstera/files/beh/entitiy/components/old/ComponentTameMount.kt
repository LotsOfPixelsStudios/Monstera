package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.lang.langKey

class ComponentTameMount {
    val general = mutableMapOf<String, Any>()

    var attemptTemperMod: Int? = null
    var autoRejectItems: ArrayList<String>? = null
    var maxTemper: Number? = null
    var minTemper: Number? = null
    var tameEvent: String? = null

    /**
     * 0..1
     *
     * call feedItem()
     * The list of items that can be used to increase the entity's temper and speed up the taming process.
     */
    fun feedItems(settings: BehEntityCompTameMountFeedItems.() -> Unit) {
        general["feed_items"] = BehEntityCompTameMountFeedItems().apply(settings).getData()
    }

    /**
     * 0..1
     *
     * The text that shows in the feeding interact button.
     */
    fun feedText(
        displayName: String? = null,
        langKey: String = "action.interact.${displayName?.replace(" ", "_")}".lowercase()
    ) {
        general["feed_text"] = langKey
        if (displayName != null)
            langKey(langKey, displayName)
    }

    /**
     * 0..1
     *
     * The text that shows in the riding interact button.
     */
    fun rideText(
        displayName: String? = null,
        langKey: String = "action.interact.${displayName?.replace(" ", "_")}".lowercase()
    ) {
        general["ride_text"] = langKey
        if (displayName != null)
            langKey(langKey, displayName)
    }

    fun getData(): MutableMap<String, Any> {
        attemptTemperMod?.let { general["attempt_temper_mod"] = it }
        autoRejectItems?.let { general["autoRejectItems"] = it }
        maxTemper?.let { general["max_temper"] = it }
        minTemper?.let { general["min_temper"] = it }
        tameEvent?.let { general["tame_event"] = it }
        return general
    }
}

class BehEntityCompTameMountFeedItems {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     *
     * @param item Name of the item this entity likes and can be used to increase this entity's temper.
     * @param temperMod The amount of temper this entity gains when fed this item.
     */
    fun feedItem(item: String, temperMod: Int = 0) {
        general.add(mutableMapOf(
            "item" to item,
            "temper_mod" to temperMod
        ))
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}