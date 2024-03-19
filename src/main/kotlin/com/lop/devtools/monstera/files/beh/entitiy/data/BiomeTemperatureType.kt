package com.lop.devtools.monstera.files.beh.entitiy.data

enum class BiomeTemperatureType {
    COLD,
    MILD,
    OCEAN,
    WARM;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}