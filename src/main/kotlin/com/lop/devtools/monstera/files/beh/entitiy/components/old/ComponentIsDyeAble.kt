package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.Config

class ComponentIsDyeAble {
    val general = mutableMapOf<String, Any>()

    var interactText: String? = null

    /**
     * @param key should look like action.interact.dye
     * @param display the text to display on mobile devices
     */
    fun interactText(key: String, display: String, config: Config? = null) {
        general["interact_text"] = key
        config?.langFileBuilder?.addonRes?.add(key, display)
    }

    fun getData(): MutableMap<String, Any> {
        interactText?.let { general["interact_text"] = it }
        return general
    }
}