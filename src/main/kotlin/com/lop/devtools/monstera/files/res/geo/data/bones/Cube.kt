package com.lop.devtools.monstera.files.res.geo.data.bones

import com.google.gson.annotations.Expose

data class Cube(
    @Expose
    val origin: ArrayList<Float>? = null,
    @Expose
    val size: ArrayList<Float>? = null,
    @Expose
    val rotation: ArrayList<Float>? = null,
    @Expose
    val uv: ArrayList<Float> = arrayListOf(0f, 0f),
    @Expose
    val mirror: Boolean? = null,
    @Expose
    val pivot: ArrayList<Float>? = null,
    @Expose
    val inflate: Float? = null
)
