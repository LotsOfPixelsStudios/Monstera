package com.lop.devtools.monstera.files.beh.entitiy.description

enum class RuntimeIdentifier(val s: String) {
    SHULKER("minecraft:shulker"),
    ENDER_CRYSTAL("minecraft:ender_crystal"),
    PARROT("minecraft:parrot"),
    ARMOR_STAND("minecraft:armor_stand"),
    IRON_GOLEM("minecraft:iron_golem"),
    ARROW("minecraft:arrow"),
    THROWN_TRIDENT("minecraft:thrown_trident"),
    PIGLIN("minecraft:piglin"),
    SPIDER("minecraft:spider"),
    MINECART("minecraft:minecart"),
    BOAT("minecraft:boat"),
    SHEEP("minecraft:sheep"),
    PANDA("minecraft:panda"),
    WITHER_SKULL_DANGEROUS("minecraft:wither_skull_dangerous"),
    ZOMBIE("minecraft:zombie"),
    SKELETON("minecraft:skeleton"),
    GUARDIAN("minecraft:guardian"),
    SNOWBLL("minecraft:snowball");

    override fun toString(): String = s
}
