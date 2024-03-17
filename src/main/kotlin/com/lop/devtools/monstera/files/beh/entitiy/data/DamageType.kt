package com.lop.devtools.monstera.files.beh.entitiy.data

import com.google.gson.annotations.SerializedName

enum class DamageType {
    @SerializedName("all")
    ALL,
    @SerializedName("anvil")
    ANVIL,
    @SerializedName("attack")
    ATTACK,
    @SerializedName("block_explosion")
    BLOCK_EXPLOSION,
    @SerializedName("charging")
    CHARGING,
    @SerializedName("contact")
    CONTACT,
    @SerializedName("drowning")
    DROWNING,
    @SerializedName("entity_attack")
    ENTITY_ATTACK,
    @SerializedName("entity_explosion")
    ENTITY_EXPLOSION,
    @SerializedName("fall")
    FALL,
    @SerializedName("falling_block")
    FALLING_BLOCK,
    @SerializedName("fatal")
    FATAL,
    @SerializedName("fire")
    FIRE,
    @SerializedName("fire_tick")
    FIRE_TICK,
    @SerializedName("fireworks")
    FIREWORKS,
    @SerializedName("fly_into_wall")
    FLY_INTO_WALL,
    @SerializedName("freezing")
    FREEZING,
    @SerializedName("lava")
    LAVA,
    @SerializedName("lighting")
    LIGHTING,
    @SerializedName("magic")
    MAGIC,
    @SerializedName("magma")
    MAGMA,
    @SerializedName("none")
    NONE,
    @SerializedName("override")
    OVERRIDE,
    @SerializedName("piston")
    PISTON,
    @SerializedName("projectile")
    PROJECTILE,
    @SerializedName("stalactite")
    STALACTITE,
    @SerializedName("stalagmite")
    STALAGMITE,
    @SerializedName("starve")
    STARVE,
    @SerializedName("suffocation")
    SUFFOCATION,
    @SerializedName("suicide")
    SUICIDE,
    @SerializedName("temperature")
    TEMPERATURE,
    @SerializedName("thorns")
    THORNS,
    @SerializedName("void")
    VOID,
    @SerializedName("wither")
    WITHER;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}