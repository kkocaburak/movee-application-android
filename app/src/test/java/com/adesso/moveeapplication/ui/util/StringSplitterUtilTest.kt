package com.adesso.moveeapplication.ui.util

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Burak Karakoca on 12.11.2020.
 */
class StringSplitterUtilTest {

    @Test
    fun `when string given, should return string list of splitted string`() {
        val string = "string, string2, string3"
        val splitter = ","

        val expected = listOf("string", " string2", " string3")
        val output = StringSplitterUtil.getStringBySplitParameter(string, splitter)

        assertEquals(expected, output)
    }
}