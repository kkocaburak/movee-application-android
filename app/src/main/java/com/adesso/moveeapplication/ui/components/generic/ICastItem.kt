package com.adesso.moveeapplication.ui.components.generic

import com.adesso.moveeapplication.data.model.tmdb.ICast
import java.io.Serializable

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
interface ICastItem : Serializable {
    val item: ICast
}