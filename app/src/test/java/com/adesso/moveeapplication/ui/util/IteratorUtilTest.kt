package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.generic.MovieItem
import com.adesso.moveeapplication.ui.components.generic.PrimaryHeaderSection
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Burak Karakoca on 12.11.2020.
 */
class IteratorUtilTest {

    @Test
    fun deleteById() {
        val mArrayList = arrayListOf<GenericItem>(
            PrimaryHeaderSection(-1,"header1",-1,1),
            PrimaryHeaderSection(-1,"header2",-1,2)
        )

        val mSelectedId = 1

        val expected = arrayListOf<GenericItem>(
            PrimaryHeaderSection(-1,"header2",-1,2)
        )

        IteratorUtil.deleteById(mArrayList, mSelectedId)

        assertEquals(expected, mArrayList)
    }

}