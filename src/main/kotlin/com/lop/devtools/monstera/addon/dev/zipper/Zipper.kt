package com.lop.devtools.monstera.addon.dev.zipper

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

object Zipper {
    fun zipDirectory(inputDirectory: File, outputZipFile: File) {
        ZipOutputStream(BufferedOutputStream(FileOutputStream(outputZipFile))).use { zos ->
            inputDirectory.walkTopDown().forEach { file ->
                val zipFileName = file.absolutePath.removePrefix(inputDirectory.absolutePath).removePrefix(File.separator)
                val entry = ZipEntry( "$zipFileName${(if (file.isDirectory) "/" else "" )}")
                zos.putNextEntry(entry)
                if (file.isFile) {
                    file.inputStream().copyTo(zos)
                }
            }
        }
    }
}

/**
 * zip a directory
 * @param outputZipFile the target file (should not exist jet)
 * @return the directory that was zipped (not the zip file)
 */
fun File.zipDir(outputZipFile: File): File {
    Zipper.zipDirectory(this, outputZipFile)
    return this
}