package com.lop.devtools.monstera.files.beh.item.comp.food

class Effects {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     */
    fun effect(name: String, chance: Float = 1.0f, duration: Int = 30, amplifier: Int = 1) {
        general.add(
            mutableMapOf(
                "name" to name,
                "chance" to chance,
                "duration" to duration,
                "amplifier" to amplifier
            )
        )
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}