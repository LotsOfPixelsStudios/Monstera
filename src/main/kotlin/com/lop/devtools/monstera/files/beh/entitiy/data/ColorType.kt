package com.lop.devtools.monstera.files.beh.entitiy.data

enum class ColorType {
    BLACK,
    BLUE,
    BROWN,
    CYAN,
    GREEN,
    LIGHT_BLUE,
    LIGHT_GREEN,
    MAGENTA,
    ORANGE,
    PINK,
    PURPLE,
    RED,
    SILVER,
    WHITE,
    YELLOW;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}