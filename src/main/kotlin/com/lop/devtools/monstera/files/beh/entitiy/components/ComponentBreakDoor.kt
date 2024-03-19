package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.DifficultyType

class ComponentBreakDoor : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            breakTime?.let { general["break_time"] = it }
            minDifficulty?.let { general["min_difficulty"] = it.toString().lowercase() }
            return general
        }
    }

    var breakTime: Float? = null
    var minDifficulty: DifficultyType? = null
}