package com.lop.devtools.monstera.files.beh.entitiy.data

import com.google.gson.annotations.SerializedName

enum class Subject {
    @SerializedName("block")
    BLOCK,

    @SerializedName("block")
    DAMAGER,

    @SerializedName("damager")
    OTHER,

    @SerializedName("other")
    PARENT,

    @SerializedName("parent")
    PLAYER,

    @SerializedName("player")
    SELF,

    @SerializedName("target")
    TARGET,

    @SerializedName("baby")
    BABY;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}