package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.SerializedName

enum class SoundCategory {
    @SerializedName("weather")
    WEATHER,

    @SerializedName("weather")
    BLOCK,

    @SerializedName("bucket")
    BUCKET,

    @SerializedName("bottle")
    BOTTLE,

    @SerializedName("ui")
    UI,

    @SerializedName("player")
    PLAYER,

    @SerializedName("hostile")
    HOSTILE,

    @SerializedName("music")
    MUSIC,

    @SerializedName("record")
    RECORD,

    @SerializedName("neutral")
    NEUTRAL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

enum class CategorySound {
    @SerializedName("block")
    BLOCK,

    @SerializedName("entity")
    ENTITY,

    @SerializedName("default_entity")
    DEFAULT_ENTITY,

    @SerializedName("individual")
    INDIVIDUAL,

    @SerializedName("interactive")
    INTERACTIVE;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}