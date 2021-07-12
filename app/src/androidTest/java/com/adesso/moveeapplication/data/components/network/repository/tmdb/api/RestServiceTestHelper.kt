package com.adesso.moveeapplication.data.components.network.repository.tmdb.api

import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Burak Karakoca on 13.11.2020.
 */
object RestServiceTestHelper {

    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}