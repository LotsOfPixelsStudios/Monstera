package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.SerializedName

enum class SoundEvent(val s: String) {
    @SerializedName("default")
    DEFAULT("default"),

    @SerializedName("hit")
    HIT("hit"),

    @SerializedName("item.use.on")
    ITEM_USE_ON("item.use.on"),

    @SerializedName("place")
    PLACE("place"),

    @SerializedName("power.on")
    POWER_ON("power.on"),

    @SerializedName("power.off")
    POWER_OFF("power.off"),

    @SerializedName("attack")
    ATTACK("attack"),

    @SerializedName("ambient")
    AMBIENT("ambient"),

    @SerializedName("ambient.in.water")
    AMBIENT_IN_WATER("ambient.in.water"),

    @SerializedName("ambient.aggressive")
    AMBIENT_AGGRESSIVE("ambient.aggressive"),

    @SerializedName("ambient.baby")
    AMBIENT_BABY("ambient.baby"),

    @SerializedName("ambient.worried")
    AMBIENT_WORRIED("ambient.worried"),

    @SerializedName("cant_breed")
    CAN_BREED("cant_breed"),

    @SerializedName("death")
    DEATH("death"),

    @SerializedName("hurt")
    HURT("hurt"),

    @SerializedName("presneeze")
    PRESNEEZE("presneeze"),

    @SerializedName("sneeze")
    SNEEZE("sneeze"),

    @SerializedName("step")
    STEP("step"),

    @SerializedName("fall.big")
    FALL_BIG("fall.big"),

    @SerializedName("fall.small")
    FALL_SMALL("fall.small"),

    @SerializedName("splash")
    SPLASH("splash"),

    @SerializedName("shoot")
    SHOOT("shoot"),

    @SerializedName("breathe")
    BREATHE("breathe"),

    @SerializedName("swim")
    SWIM("swim"),

    @SerializedName("death.in.water")
    DEATH_IN_WATER("death.in.water"),

    @SerializedName("jump")
    JUMP("jump"),

    @SerializedName("eat")
    EAT("eat"),

    @SerializedName("hurt.in.water")
    HURT_IN_WATER("hurt.in.water"),

    @SerializedName("mad")
    MAD("mad"),

    @SerializedName("stare")
    STARE("stare"),

    @SerializedName("sniff")
    SNIFF("sniff"),

    @SerializedName("sleep")
    SLEEP("default"),

    @SerializedName("spit")
    SPIT("spit"),

    @SerializedName("warn")
    WARN("warn"),

    @SerializedName("scream")
    SCREAM("scream");

    override fun toString() = s
}