package com.lop.devtools.monstera.files

import java.io.File
import java.io.FileNotFoundException
import java.nio.ByteBuffer
import java.util.*

/**
 * load a directory or a file from the resource directory
 * save to use in pipelines
 *
 * @param path the relative path within the resource directory
 * @sample getResourceSample
 */
fun getResource(path: String): File {
    return File(
        Thread.currentThread().contextClassLoader?.getResource(path)?.path
            ?: throw FileNotFoundException("This File (with path: '$path') does not exist, check spelling!")
    )
}

private fun getResourceSample() {
    getResource("entity/default_texture.png")
}

fun getValueForLangKey(lanFile: File, key: String): String? {
    if (lanFile.exists()) {
        val data = lanFile.readText()
        data.split("\n").forEach {
            if (it.contains(key)) {
                return it.removePrefix("$key=").replace("\n", "").replace("\r", "")
            }
        }
    }
    return null
}

/**
 * @return the file name with a unique hash, if the file is the same, the return value is the same
 */
fun getUniqueFileName(file: File): String {
    val hash = file.path.split("resource").last().hashCode()
    val buff = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(hash).array()
    val enc = Base64
        .getEncoder()
        .encodeToString(buff)
        .replace("=", "")
        .replace("+", "")
        .replace("/", "")
    return enc + "_" + file.name
}

fun File(vararg parents: String): File {
    return File(parents.joinToString(separator = File.separator) {
        it
    })
}

fun File.createWithDirs(): File {
    this.parentFile.mkdirs()
    return this
}
