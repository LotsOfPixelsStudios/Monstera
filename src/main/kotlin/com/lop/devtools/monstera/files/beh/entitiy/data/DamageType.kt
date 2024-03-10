package com.lop.devtools.monstera.files.beh.entitiy.data

enum class DamageType {
    ALL,
    ANVIL,
    ATTACK,
    BLOCK_EXPLOSION,
    CHARGING,
    CONTACT,
    DROWNING,
    ENTITY_ATTACK,
    ENTITY_EXPLOSION,
    FALL,
    FALLING_BLOCK,
    FATAL,
    FIRE,
    FIRE_TICK,
    FIREWORKS,
    FLY_INTO_WALL,
    FREEZING,
    LAVA,
    LIGHTING,
    MAGIC,
    MAGMA,
    NONE,
    OVERRIDE,
    PISTON,
    PROJECTILE,
    STALACTITE,
    STALAGMITE,
    STARVE,
    SUFFOCATION,
    SUICIDE,
    TEMPERATURE,
    THORNS,
    VOID,
    WITHER;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}