package com.lop.devtools.monstera.files.beh.tables.trading

class BehEcoGroup {
    var general = mutableMapOf<String, Any>()
    val trades = arrayListOf<MutableMap<String, Any>>()

    var numToSelect: Int? = null

    /**
     * 1
     *
     * @sample sampleTrades
     */
    fun trade(trade: BehEcoTrade.() -> Unit) {
        trades.add(BehEcoTrade().apply(trade).getData())
    }

    fun getData(): Map<String, Any> {
        numToSelect?.let { general["num_to_select"] = it }
        general["trades"] = trades
        return general
    }

    private fun sampleTrades() {
        trade {
            wants("minecraft:emerald", 1)
            gives("minecraft:stick", 6) {  }
            rewardExp = true
            maxUses = -1
        }
    }
}