package com.lop.devtools.monstera.files.res.geo.data.bones

import com.google.gson.annotations.Expose

data class GeoBone(
    @Expose
    var name: String,
    @Expose
    var parent: String? = null,
    @Expose
    var pivot: ArrayList<Float>? = null,
    @Expose
    var rotation: ArrayList<Float>? = null,
    @Expose
    var cubes: ArrayList<Cube>? = null,
    @Expose
    var locators: MutableMap<String, Locator>? = null
)
