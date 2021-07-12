package com.adesso.moveeapplication.ui.components.movierecycler

/**
 * Created by Burak Karakoca on 25.09.2020.
 */
enum class RecyclerViewTypes(val value: Int) {
    PRIMARY_HEADER_SECTION(0), SECONDARY_HEADER_SECTION(2),
    CAROUSEL_ITEM(1), MOVIE_ITEM(3), TV_ITEM(4), CAST(5)
}