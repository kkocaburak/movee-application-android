package com.adesso.moveeapplication.ui.util

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Burak Karakoca on 12.11.2020.
 */
class StringCompilerUtilTest {

    @Test
    fun `when two string given should return compile of two string`() {
        val string1 = "string1"
        val string2 = "string2"

        val expected = string1 + string2
        val output = StringCompilerUtil.compileString(string1, string2)

        assertEquals(expected, output)
    }

    @Test
    fun `when non-string type given should return compile of two variable`() {
        val string = "string1"
        val int = 5

        val expected = string + int.toString()
        val output = StringCompilerUtil.compileString(string, int)

        assertEquals(expected, output)
    }

    @Test
    fun `when three string given should return compile of three string`() {
        val string1 = "string1"
        val string2 = "string2"
        val string3 = "string3"

        val expected = string1 + string2 + string3
        val output = StringCompilerUtil.compileString(string1, string2, string3)

        assertEquals(expected, output)
    }

    @Test
    fun `when non-string type given should return compile of three variable`() {
        val string = "string1"
        val int = 5
        val string2 = "string2"

        val expected = string + int.toString() + string2
        val output = StringCompilerUtil.compileString(string, int, string2)

        assertEquals(expected, output)
    }

    @Test
    fun `when a string given should return compile of string and comma`() {
        val string1 = "string1"

        val expected = "$string1,"
        val output = StringCompilerUtil.compileWithComma(string1)

        assertEquals(expected, output)
    }

    @Test
    fun `when a non-string type given should return compile of variable and comma`() {
        val int = 15

        val expected = "$int,"
        val output = StringCompilerUtil.compileWithComma(int)

        assertEquals(expected, output)
    }

    @Test
    fun `when a string given should return compile of string and dot`() {
        val string1 = "string1"

        val expected = "$string1."
        val output = StringCompilerUtil.compileWithDot(string1)

        assertEquals(expected, output)
    }

    @Test
    fun `when a non-string type given should return compile of variable and dot`() {
        val int = 20

        val expected = "$int."
        val output = StringCompilerUtil.compileWithDot(int)

        assertEquals(expected, output)
    }
}