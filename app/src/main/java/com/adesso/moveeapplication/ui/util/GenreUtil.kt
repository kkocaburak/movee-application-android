package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel

/**
 * Created by Burak Karakoca on 7.10.2020.
 */
object GenreUtil {

    fun getGenre(genreList: List<Int>): String {
        val stringBuilder = StringBuilder()

        genreList.forEach { genreId ->
            val currentGenreName: String? = getNameOfGenre(genreId)
            currentGenreName?.let { genreName ->
                StringBuilderUtil.checkStringBuilder(stringBuilder)
                stringBuilder.append(genreName)
            }
        }
        return stringBuilder.toString()
    }
    private fun getNameOfGenre(id: Int): String? {
        MainViewModel.genreList.forEach { genre ->
            if (genre.id == id) {
                return genre.name
            }
        }
        return null
    }

}