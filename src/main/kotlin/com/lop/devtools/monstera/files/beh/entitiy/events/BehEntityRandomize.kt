package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.getMonsteraLogger

class BehEntityRandomize {

    /**
     * saves the data for all randomized sub events,
     * add events through functions below
     */
    val randomEvents: MutableList<BehEntityEvent.ContainsWeight> = mutableListOf()

    fun add(weight: Int, vararg groups: String) {
        randomComp {
            this.weight = weight
            add { componentGroups = groups.asList().toMutableList() }
        }
    }

    fun remove(weight: Int, vararg groups: String) {
        randomComp {
            this.weight = weight
            remove { componentGroups = groups.asList().toMutableList() }
        }
    }

    /**
     * 1..*
     */
    fun randomComp(settings: BehEntityEvent.ContainsWeight.() -> Unit) {
        randomEvents.add(BehEntityEvent.ContainsWeight().apply(settings).also {
            if (it.weight == null)
                getMonsteraLogger(this.javaClass.name)
                    .warn("Randomized event component has no weight! Set with 'randomComp { weight = 1 }'.")
        })
    }
}