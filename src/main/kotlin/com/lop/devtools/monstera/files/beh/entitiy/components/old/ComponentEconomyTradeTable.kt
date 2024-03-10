package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.beh.tables.trading.BehEconomyTrades

class ComponentEconomyTradeTable {
    val general = mutableMapOf<String, Any>()

    var table: String? = null
    var newScreen: Boolean? = null
    var persistTrades: Boolean? = null
    var convertTradesEconomy: Boolean? = null
    var heroDemandDiscount: Int? = null
    var nearbyCuredDiscount: Int? = null
    var showTradeScreen: Boolean? = null

    /**
     * 0..1
     *
     * Name to be displayed while trading with this entity
     * @param key mandatory usually entity.<entity_name>.<sub_name> like "entity.villager.farmer"
     * @param displayName the actual display name, a lang key is automatically generated, leave blank to not do that
     */
    fun displayName(key: String, displayName: String? = null, config: Config? = null) {
        general["display_name"] = key
        if(displayName != null && config != null) {
            config.langFileBuilder.addonRes.add(key, displayName)
        }
    }

    /**
     * 0..1
     *
     * @param tableName the name of the table, most likely the name of the entity
     */
    fun table(addon: Addon, tableName: String, table: BehEconomyTrades.() -> Unit) {
        general["table"] = "trading/economy_trades/$tableName"
        BehEconomyTrades().apply(table).unsafe.build(tableName, addon.config.paths.behTrading.resolve("economy_trades"))
    }

    /**
     * 0..1
     *
     * How much should Demand be modified by when the player has cured the Zombie Villager.
     * Can be specified as a pair of numbers (low-tier trade discount and high-tier trade discount)
     */
    fun curedDiscount(min: Int, max: Int = min) {
        general["cured_discount"] = arrayListOf(min, max)
    }

    /**
     * 0..1
     *
     * The max Demand should be modified by when the player has cured the Zombie Villager.
     * Can be specified as a pair of numbers (low-tier trade discount and high-tier trade discount)
     */
    fun maxCuredDiscount(min: Int, max: Int = min) {
        general["max_cured_discount"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        table?.let { general["table"] = it }
        newScreen?.let { general["new_screen"] = it }
        persistTrades?.let { general["persist_trades"] = it }
        convertTradesEconomy?.let { general["convert_trades_economy"] = it }
        heroDemandDiscount?.let { general["hero_demand_discount"] = it }
        nearbyCuredDiscount?.let { general["nearby_cured_discount"] = it }
        showTradeScreen?.let { general["show_trade_screen"] = it }
        return general
    }
}