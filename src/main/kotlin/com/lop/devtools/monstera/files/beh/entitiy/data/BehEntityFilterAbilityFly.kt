package com.lop.devtools.monstera.files.beh.entitiy.data

enum class BehEntityFilterAbilityFly {
    FLYSPEED,
    FLYING,
    INSTABUILD,
    INVULNERABLE,
    LIGHTING,
    MAYFLY,
    MUTE,
    NOCLIP,
    WALKSPEED,
    WORLDBUILDER;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}