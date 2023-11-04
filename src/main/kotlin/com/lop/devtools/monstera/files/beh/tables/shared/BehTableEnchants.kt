package com.lop.devtools.monstera.files.beh.tables.shared

class BehTableEnchants {
    var general = ArrayList<Any>()

    /**
     * 1..*
     *
     * @param id: enchant name like knockback, unbreaking
     * @param level: usually form 1 to 5
     */
    fun enchant(id: String, level: Int) {
        general.add(
            mutableMapOf(
                "id" to id,
                "level" to level
            )
        )
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}