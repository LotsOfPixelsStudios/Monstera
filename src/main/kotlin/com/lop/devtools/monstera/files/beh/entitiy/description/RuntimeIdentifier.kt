package com.lop.devtools.monstera.files.beh.entitiy.description

enum class RuntimeIdentifier(val s: String) {
    SHULKER("shulker"),
    ENDER_CRYSTAL("ender_crystal"),
    PARROT("parrot"),
    ARMOR_STAND("armor_stand"),
    IRON_GOLEM("iron_golem"),
    ARROW("arrow"),
    THROWN_TRIDENT("thrown_trident"),
    PIGLIN("piglin"),
    SPIDER("spider"),
    MINECART("minecart"),
    BOAT("boat"),
    SHEEP("sheep"),
    PANDA("panda"),
    WITHER_SKULL_DANGEROUS("wither_skull_dangerous"),
    ZOMBIE("zombie"),
    SKELETON("skeleton"),
    GUARDIAN("guardian"),
    SNOWBLL("snowball");

    override fun toString(): String = s
}
