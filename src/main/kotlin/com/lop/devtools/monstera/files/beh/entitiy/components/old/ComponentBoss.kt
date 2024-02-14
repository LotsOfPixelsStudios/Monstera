package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBoss : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        var displayName: String? = null
        var langKey: String? = "${displayName?.replace(" ", "_")}.boss.bar".lowercase()

        override fun getData(): MutableMap<String, Any> {
            hudRange?.let { general["hud_range"] = it }
            shouldDarkenSky?.let { general["should_darken_sky"] = it }
            langKey?.let { general["name"] = it }
            return general
        }
    }

    var hudRange: Int? = null
    var shouldDarkenSky: Boolean? = null

    fun setDisplayName(
        name: String,
        langKey: String = "${name.replace(" ", "_")}.boss.bar".lowercase(),
        config: Config? = null
    ) {
        unsafe.displayName = name
        unsafe.langKey = langKey
        config?.langFileBuilder?.addonRes?.add(langKey, name)
    }
}