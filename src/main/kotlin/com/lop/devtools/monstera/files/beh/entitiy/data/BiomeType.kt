package com.lop.devtools.monstera.files.beh.entitiy.data

enum class BiomeType {
    BEACH,
    DESERT,
    EXTREME_HILLS,
    FLAT,
    FOREST,
    ICE,
    JUNGLE,
    MESA,
    MUSHROOM_ISLAND,
    OCEAN,
    PLAIN,
    RIVER,
    SAVANNA,
    STONE_BEACH,
    SWAMP,
    TAIGA,
    THE_END,
    THE_NETHER;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}