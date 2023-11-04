package com.lop.devtools.monstera.files.beh.tables.trading

class BehEcoTier {
    var general = mutableMapOf<String, Any>()
    var groupList = arrayListOf<Any>()
    val trades = arrayListOf<MutableMap<String, Any>>()

    var totalExpRequired: Int? = null

    /**
     * 1..*
     *
     * @sample sampleGroup
     */
    fun group(settings: BehEcoGroup.() -> Unit) {
        val behEcoGroup = BehEcoGroup().apply { settings(this) }
        groupList.add(behEcoGroup.getData())
    }

    /**
     * 1
     *
     * @sample sampleTrades
     */
    fun trade(trade: BehEcoTrade.() -> Unit) {
        trades.add(BehEcoTrade().apply(trade).getData())
    }

    fun getData(): Map<String, Any> {
        totalExpRequired?.let { general["total_exp_required"] = it }
        if(groupList.size != 0) {
            general["groups"] = groupList
        }
        if(trades.size != 0) {
            general["trades"] = trades
        }
        return general
    }

    private fun sampleGroup() {
        group {
            numToSelect = 2
            trade {
                wants("minecraft:emerald", 1)
                gives("minecraft:stick", 6) {  }
                rewardExp = false
                maxUses = -1
            }
        }
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