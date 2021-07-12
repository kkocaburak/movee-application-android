package com.adesso.moveeapplication.data.model.tmdb

import java.io.Serializable

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
interface ITvSeries : Serializable {
    val name: String
    val id: Int
    val overview: String
    val genreIds: List<Int>?
    val voteAverage: Double
    val posterPath: String?
    val firstAirDate: String?
}