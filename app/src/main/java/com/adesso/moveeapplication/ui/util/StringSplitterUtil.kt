package com.adesso.moveeapplication.ui.util

/**
 * Created by Burak Karakoca on 13.10.2020.
 */
object StringSplitterUtil {
    fun getStringBySplitParameter(string: String, splitter: String) : List<String> {
        return string.split(splitter)
    }
}