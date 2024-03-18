package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.config

object TestAddon {
    val lazyAddon: Addon by lazy {
        addon(config("test_addon") {
            projectShort = "ta"
        }) {
            buildToMcFolder = false
        }
        Addon.active!!
    }
}