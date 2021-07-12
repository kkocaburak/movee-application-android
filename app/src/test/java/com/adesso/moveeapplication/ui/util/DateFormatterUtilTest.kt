package com.adesso.moveeapplication.ui.util

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.exp

/**
 * Created by Burak Karakoca on 12.11.2020.
 */
class DateFormatterUtilTest {

    @Test
    fun `when birth date and place of birth given, should return mmmm dd, yyyy in CountryName`() {
        val mBirthDate = "1955-03-19"
        val mPlaceOfBirth = "Idar-Oberstein, Germany"

        val expected = "Mart 19, 1955  in  Germany."
        val output = DateFormatterUtil.formatDate(mBirthDate, mPlaceOfBirth)
        
        assertEquals(expected, output)
    }

    @Test
    fun `when birth date and place of birth given is null, should return mmmm dd, yyyy in CountryName`() {
        val mBirthDate = ""
        val mPlaceOfBirth = "Idar-Oberstein, Germany"

        val expected = "Germany."
        val output = DateFormatterUtil.formatDate(mBirthDate, mPlaceOfBirth)

        assertEquals(expected, output)
    }

}