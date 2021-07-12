package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.data.model.tmdb.Genre
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Burak Karakoca on 12.11.2020.
 */
class GenreUtilTest {

    @Test
    fun `when genre list correct, should return genre names`() {
        val mGenreList = listOf(28, 53)
        MainViewModel.genreList = arrayListOf(
            Genre(28, "Aksiyon"),
            Genre(53, "Gerilim")
        )

        val expected = "Aksiyon, Gerilim"
        val output = GenreUtil.getGenre(mGenreList)

        assertEquals(expected, output)
    }

    @Test
    fun `when genre list incorrect, should return empty`() {
        val mGenreList = listOf(-1, -2)
        MainViewModel.genreList = arrayListOf(
            Genre(28, "Aksiyon"),
            Genre(53, "Gerilim")
        )

        val expected = ""
        val output = GenreUtil.getGenre(mGenreList)

        assertEquals(expected, output)
    }
}