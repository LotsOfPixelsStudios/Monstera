package com.lop.devtools.monstera.files.res.sounds

enum class SoundCategory {
    WEATHER,
    BLOCK,
    BUCKET,
    BOTTLE,
    UI,
    PLAYER,
    HOSTILE,
    MUSIC,
    RECORD,
    NEUTRAL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

enum class CategorySound {
    BLOCK,
    ENTITY,
    DEFAULT_ENTITY,
    INDIVIDUAL,
    INTERACTIVE;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}