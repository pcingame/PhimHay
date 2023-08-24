@file:Suppress("unused")
package com.pcingame.phimhay.common.utils

import com.pcingame.phimhay.common.DebugLog
import com.pcingame.phimhay.di.App
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

object FileUtils {
    private const val TAB = "\t"
    private const val CRLF = "\r\n"

    /**
     * Available space in internal storage
     */
    val availableInternalFreeSpace: Long
        get() = try {
            File(App.context.filesDir.absoluteFile.toString()).freeSpace
        } catch (e: IOException) {
            DebugLog().e(e.message.toString())
            0L
        }

    fun folder(parent: File, name: String): File = File(parent, name).apply {
        if (!exists()) mkdirs()
    }

    fun copyToFile(fromFile: File, toFile: File) {
        fromFile.inputStream().use { inputStream ->
            toFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }

    /**
     * Create new file in folder with content
     * @param directory: the parent folder
     * @param fileName: name of the output file need to create
     * @param records: Each record will be write to one line in output file
     */
    private fun writeFile(
        directory: File,
        fileName: String,
        records: List<List<String>>
    ) {
        if (!directory.exists()) directory.mkdirs()
        if (!directory.isDirectory) return
        try {
            val file = File(directory, fileName)
            val writer = FileWriter(file)
            writer.run {
                for (record in records) {
                    for (item in record) {
                        append(item).append(TAB)
                    }
                    append(CRLF)
                }
                flush()
                close()
            }
        } catch (e: IOException) {
            DebugLog().e(e.message.toString())
        }
    }

    /**
     * Create new file in folder with content
     * @param directory: the parent folder
     * @param fileName: name of the output file need to create
     * @param onWrite: Callback to write
     */
    private fun writeFile(
        directory: File,
        fileName: String,
        onWrite: FileWriter.() -> Unit
    ) {
        if (!directory.exists()) directory.mkdirs()
        if (!directory.isDirectory) return
        try {
            val file = File(directory.path, fileName)
            val writer = FileWriter(file)
            writer.run {
                onWrite()
                flush()
                close()
            }
        } catch (e: IOException) {
            DebugLog().e(e.message.toString())
        }
    }

    fun delete(vararg files: File) {
        for (file in files) {
            try {
                if (file.isDirectory) file.deleteRecursively() else file.delete()
            } catch (e: IOException) {
                DebugLog().e(e.message.toString())
            }
        }
    }
}
