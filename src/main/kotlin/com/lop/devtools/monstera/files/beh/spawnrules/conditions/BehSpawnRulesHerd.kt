package com.lop.devtools.monstera.files.beh.spawnrules.conditions

class BehSpawnRulesHerd {
    val general = mutableMapOf<String, Any>()

    /**
     * 0..1
     *
     * This is an event that can be triggered from spawning
     */
    fun event(value: String) {
        general.apply {
            put("event", value)
        }
    }

    /**
     * 0..1
     *
     * This is the number of mobs spawned before the specified event is triggered
     * @param value: might be a Float (prob not)
     */
    fun eventSkipCount(value: Int) {
        general.apply {
            put("event_skip_count", value)
        }
    }

    /**
     * 1
     *
     * This is the maximum number of mobs that spawn in a herd
     */
    fun maxSize(value: Int) {
        general.apply {
            put("max_size", value)
        }
    }

    /**
     * 1
     *
     * 	This is the minimum number of mobs that spawn in a herd
     */
    fun minSize(value: Int) {
        general.apply {
            put("min_size", value)
        }
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}