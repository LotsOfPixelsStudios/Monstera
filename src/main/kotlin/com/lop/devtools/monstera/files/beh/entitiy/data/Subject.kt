package com.lop.devtools.monstera.files.beh.entitiy.data

enum class Subject {
    BLOCK,
    DAMAGER,
    OTHER,
    PARENT,
    PLAYER,
    SELF,
    TARGET,
    BABY;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}