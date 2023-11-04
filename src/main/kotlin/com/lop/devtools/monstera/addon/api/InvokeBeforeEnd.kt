package com.lop.devtools.monstera.addon.api

import com.lop.devtools.monstera.addon.Addon

interface InvokeBeforeEnd {
    fun invoke(addon: Addon)
}