package com.lop.devtools.monstera.files.res.geo.data.bones

import com.google.gson.annotations.Expose

data class Locator(
    @Expose
    var offset: MutableList<Number>? = null,
    @Expose
    var rotation: MutableList<Number>? = null
)