package com.fahim.projectseries

import android.os.Environment
import java.io.File

object ExternalStorage {
    fun write(data: String) {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "Notes1.txt"
        )
        file.writeText(data)

    }

    fun read(): String {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "Notes1.txt"
        )
        if (file.exists()) return file.readText()
        return ""
    }
}














