package com.lop.devtools.monstera.addon.api

import java.nio.file.Path

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

interface MonsteraBuildableFile {
    /**
     * build the file
     *
     * @param filename the name of the file
     * @param path if null, uses the most likely path
     * @param version if null, uses the most likely version, update for example for the res entity
     */
    fun build(
        filename: String,
        path: Path? = null,
        version: String? = null
    ): Result<Path>
}

interface MonsteraUnsafe

interface MonsteraUnsafeMap : MonsteraUnsafe {
    /**
     * receive the data of a MonsteraFile, call this function instead of `unsafe.general` as this map might not have all
     * data
     */
    fun getData(): MutableMap<String, Any>
}

interface MonsteraUnsafeList : MonsteraUnsafe {
    /**
     * receive the data of a MonsteraFile, call this function instead of `unsafe.general` as this map might not have all
     * data
     */
    fun getData(): List<Any>
}

