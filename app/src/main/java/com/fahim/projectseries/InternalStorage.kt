package com.fahim.projectseries

import android.content.Context
import java.io.File

object InternalStorage {
    fun write(context: Context, data: String) {
        val file = File(context.filesDir, "Notes.txt")
        file.writeText(data)
    }

    fun read(context: Context): String {
        val file = File(context.filesDir, "Notes.txt")
        if (file.exists()) return file.readText()
        return ""

    }
}