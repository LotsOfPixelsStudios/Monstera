package com.lop.devtools.monstera.files.beh.spawnrules.conditions

enum class BiomeTag {
    ANIMAL,
    BEACH,
    BIRCH,
    COLD,
    DARK_OAK,
    DEEP,
    DESERT,
    EDGE,
    EXTREME_HILLS,
    FLOWER_FOREST,
    FOREST,
    FROZEN,
    HILLS,
    ICE,
    ICE_PLAINS,
    JUNGLE,
    LAKES,
    LUKEWARM,
    MEGA,
    MESA,
    MONSTER,
    MOOSHROOM_ISLAND,
    MOUNTAIN,
    MUTATED,
    NETHER,
    OCEAN,
    PLAINS,
    PLATEAU,
    RIVER,
    ROOFED,
    SAVANNA,
    SHORE,
    STONE,
    SWAMP,
    TAIGA,
    THE_END,
    WARM;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}