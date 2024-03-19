package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Transformation : MonsteraRawFile() {
    @SerializedName("translation")
    @Expose
    var translation: MutableList<Number>? = null
    
    fun translation(x: Number, y: Number, z: Number) {
        translation = mutableListOf(x, y, z)
    }
    
    @SerializedName("rotation")
    @Expose
    var rotation: MutableList<Number>? = null

    fun rotation(x: Number, y: Number, z: Number) {
        rotation = mutableListOf(x, y, z)
    }
    
    @SerializedName("scale")
    @Expose
    var scale: MutableList<Number>? = null

    fun scale(x: Number, y: Number, z: Number) {
        scale = mutableListOf(x, y, z)
    }
}