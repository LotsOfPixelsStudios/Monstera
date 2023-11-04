package com.lop.devtools.monstera.files.res.geo.data.bones

data class Cube(
    val origin: ArrayList<Float>? = null,
    val size: ArrayList<Float>? = null,
    val rotation: ArrayList<Float>? = null,
    val uv: ArrayList<Float> = arrayListOf(0f, 0f),
    val mirror: Boolean? = null,
    val pivot: ArrayList<Float>? = null,
    val inflate: Float? = null
)
