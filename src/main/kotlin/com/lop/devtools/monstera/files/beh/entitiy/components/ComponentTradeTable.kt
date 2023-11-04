package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.lang.langKey

class ComponentTradeTable {
    val general = mutableMapOf<String, Any>()

    var convertTradesEconomy: Boolean? = null
    var newScreen: Boolean? = null
    var persistTrades: Boolean? = null
    var table: String? = null

    /**
     * 0..1
     *
     * Name to be displayed while trading with this entity.
     */
    fun displayName(
        key: String,
        displayName: String? = null
    ) {
        general["display_name"] = key
        if (displayName != null)
            langKey(key, displayName)
    }

    fun getData(): MutableMap<String, Any> {
        convertTradesEconomy?.let { general["convert_trades_economy"] = it }
        newScreen?.let { general["new_screen"] = it }
        persistTrades?.let { general["persist_trades"] = it }
        table?.let { general["table"] = it }
        return general
    }
}