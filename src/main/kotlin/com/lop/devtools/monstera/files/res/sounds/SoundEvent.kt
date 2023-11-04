package com.lop.devtools.monstera.files.res.sounds

enum class SoundEvent(val id: String) {
    DEFAULT("default"),
    HIT("hit"),
    ITEM_USE_ON("item.use.on"),
    PLACE("place"),
    POWER_ON("power.on"),
    POWER_OFF("power.off"),

    ATTACK("attack"),
    AMBIENT("ambient"),
    AMBIENT_IN_WATER("ambient.in.water"),
    AMBIENT_AGGRESSIVE("ambient.aggressive"),
    AMBIENT_BABY("ambient.baby"),
    AMBIENT_WORRIED("ambient.worried"),
    CAN_BREED("cant_breed"),
    DEATH("death"),
    HURT("hurt"),
    PRESNEEZE("presneeze"),
    SNEEZE("sneeze"),
    STEP("step"),
    FALL_BIG("fall.big"),
    FALL_SMALL("fall.small"),
    SPLASH("splash"),
    SHOOT("shoot"),
    BREATHE("breathe"),
    SWIM("swim"),
    DEATH_IN_WATER("death.in.water"),
    JUMP("jump"),
    EAT("eat"),
    HURT_IN_WATER("hurt.in.water"),
    MAD("mad"),
    STARE("stare"),
    SNIFF("sniff"),
    SLEEP("sleep"),
    SPIT("spit"),
    WARN("warn"),
    SCREAM("scream");

    override fun toString(): String {
        return id
    }
}