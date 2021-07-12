package com.adesso.moveeapplication.data.components.network.repository.tmdb.util

/**
 * Created by Burak Karakoca on 7.10.2020.
 */
object ListJoinerUtil {

    fun <T> concatenate(vararg lists: List<T>): List<T> {
        val result: MutableList<T> = ArrayList()
        for (list in lists) {
            result += list
        }
        return result
    }

}