package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.SerializedName

enum class SoundEvent(val s: String) {
    @SerializedName("default")
    DEFAULT("default"),

    @SerializedName("hit")
    HIT("default"),

    @SerializedName("item.use.on")
    ITEM_USE_ON("default"),

    @SerializedName("place")
    PLACE("default"),

    @SerializedName("power.on")
    POWER_ON("default"),

    @SerializedName("power.off")
    POWER_OFF("default"),

    @SerializedName("attack")

    ATTACK("default"),

    @SerializedName("ambient")
    AMBIENT("default"),

    @SerializedName("ambient.in.water")
    AMBIENT_IN_WATER("default"),

    @SerializedName("ambient.aggressive")
    AMBIENT_AGGRESSIVE("default"),

    @SerializedName("ambient.baby")
    AMBIENT_BABY("default"),

    @SerializedName("ambient.worried")
    AMBIENT_WORRIED("default"),

    @SerializedName("cant_breed")
    CAN_BREED("default"),

    @SerializedName("death")
    DEATH("default"),

    @SerializedName("hurt")
    HURT("default"),

    @SerializedName("presneeze")
    PRESNEEZE("default"),

    @SerializedName("sneeze")
    SNEEZE("default"),

    @SerializedName("step")
    STEP("default"),

    @SerializedName("fall.big")
    FALL_BIG("default"),

    @SerializedName("fall.small")
    FALL_SMALL("default"),

    @SerializedName("splash")
    SPLASH("default"),

    @SerializedName("shoot")
    SHOOT("default"),

    @SerializedName("breathe")
    BREATHE("default"),

    @SerializedName("swim")
    SWIM("default"),

    @SerializedName("death.in.water")
    DEATH_IN_WATER("default"),

    @SerializedName("jump")
    JUMP("default"),

    @SerializedName("eat")
    EAT("default"),

    @SerializedName("hurt.in.water")
    HURT_IN_WATER("default"),

    @SerializedName("mad")
    MAD("default"),

    @SerializedName("stare")
    STARE("default"),

    @SerializedName("sniff")
    SNIFF("default"),

    @SerializedName("sleep")
    SLEEP("default"),

    @SerializedName("spit")
    SPIT("default"),

    @SerializedName("warn")
    WARN("default"),

    @SerializedName("scream")
    SCREAM("default");

    override fun toString() = s
}