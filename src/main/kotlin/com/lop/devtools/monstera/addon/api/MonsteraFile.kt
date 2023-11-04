package com.lop.devtools.monstera.addon.api

interface MonsteraFile {
    /**
     * an unsafe to use object where raw data is saved, generally not needed when developing an addon
     * exceptions might be not supported components add them via
     *
     * ```
     * unsafe.general["my_component"] = value
     * ```
     */
    val unsafe: MonsteraUnsafe
}

interface MonsteraUnsafe

interface MonsteraUnsafeMap: MonsteraUnsafe {
    /**
     * receive the data of a MonsteraFile, call this function instead of `unsafe.general` as this map might not have all
     * data
     */
    fun getData(): MutableMap<String, Any>
}

interface MonsteraUnsafeList: MonsteraUnsafe {
    /**
     * receive the data of a MonsteraFile, call this function instead of `unsafe.general` as this map might not have all
     * data
     */
    fun getData(): List<Any>
}

