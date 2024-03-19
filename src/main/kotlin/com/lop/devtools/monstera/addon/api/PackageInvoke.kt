package com.lop.devtools.monstera.addon.api

import com.lop.devtools.monstera.addon.Addon

/**
 * this interface gives monstera the opportunity to execute code when packaging,
 * thus this interface should not be used to add files to the main build files
 */
interface PackageInvoke {
    fun invoke(addon: Addon)
}