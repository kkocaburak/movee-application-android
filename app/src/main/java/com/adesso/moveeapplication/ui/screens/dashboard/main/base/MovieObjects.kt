package com.adesso.moveeapplication.ui.screens.dashboard.main.base

import com.adesso.moveeapplication.data.model.tmdb.Genre
import com.adesso.moveeapplication.ui.components.generic.GenericItem

/**
 * Created by Burak Karakoca on 2.11.2020.
 */
object MovieObjects {
    var genreList: ArrayList<Genre> = arrayListOf()
    var movieVisionList: List<GenericItem> = ArrayList()
    var tvPopularList: List<GenericItem> = ArrayList()
}