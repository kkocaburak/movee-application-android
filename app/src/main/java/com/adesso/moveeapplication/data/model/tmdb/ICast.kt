package com.adesso.moveeapplication.data.model.tmdb

import java.io.Serializable

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
interface ICast : Serializable {
    val id: Int
    val name : String?
    val profilePath: String?
}