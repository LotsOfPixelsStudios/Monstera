package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.SerializedName

enum class SoundEvent {
    @SerializedName("default")
    DEFAULT,

    @SerializedName("hit")
    HIT,

    @SerializedName("item.use.on")
    ITEM_USE_ON,

    @SerializedName("place")
    PLACE,

    @SerializedName("power.on")
    POWER_ON,

    @SerializedName("power.off")
    POWER_OFF,

    @SerializedName("attack")

    ATTACK,

    @SerializedName("ambient")
    AMBIENT,

    @SerializedName("ambient.in.water")
    AMBIENT_IN_WATER,

    @SerializedName("ambient.aggressive")
    AMBIENT_AGGRESSIVE,

    @SerializedName("ambient.baby")
    AMBIENT_BABY,

    @SerializedName("ambient.worried")
    AMBIENT_WORRIED,

    @SerializedName("cant_breed")
    CAN_BREED,

    @SerializedName("death")
    DEATH,

    @SerializedName("hurt")
    HURT,

    @SerializedName("presneeze")
    PRESNEEZE,

    @SerializedName("sneeze")
    SNEEZE,

    @SerializedName("step")
    STEP,

    @SerializedName("fall.big")
    FALL_BIG,

    @SerializedName("fall.small")
    FALL_SMALL,

    @SerializedName("splash")
    SPLASH,

    @SerializedName("shoot")
    SHOOT,

    @SerializedName("breathe")
    BREATHE,

    @SerializedName("swim")
    SWIM,

    @SerializedName("death.in.water")
    DEATH_IN_WATER,

    @SerializedName("jump")
    JUMP,

    @SerializedName("eat")
    EAT,

    @SerializedName("hurt.in.water")
    HURT_IN_WATER,

    @SerializedName("mad")
    MAD,

    @SerializedName("stare")
    STARE,

    @SerializedName("sniff")
    SNIFF,

    @SerializedName("sleep")
    SLEEP,

    @SerializedName("spit")
    SPIT,

    @SerializedName("warn")
    WARN,

    @SerializedName("scream")
    SCREAM;
}