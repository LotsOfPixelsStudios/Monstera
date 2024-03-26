package com.lop.devtools.monstera.files.beh.entitiy.data

enum class SlotDomain {
    ANY,
    ARMOR,
    FEET,
    HAND,
    HEAD,
    LEG,
    TORSO;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}