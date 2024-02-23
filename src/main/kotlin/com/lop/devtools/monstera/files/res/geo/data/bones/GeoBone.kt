package com.lop.devtools.monstera.files.res.geo.data.bones

import com.google.gson.annotations.Expose

data class GeoBone(
    @Expose
    val name: String,
    @Expose
    val parent: String? = null,
    @Expose
    val pivot: ArrayList<Float>? = null,
    @Expose
    val rotation: ArrayList<Float>? = null,
    @Expose
    val cubes: ArrayList<Cube>? = null,
)
