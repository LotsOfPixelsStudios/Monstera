package com.lop.devtools.monstera.files.beh.entitiy.data

enum class DifficultyType {
    EASY,
    HARD,
    NORMAL,
    PEACEFUL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}