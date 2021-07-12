package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.components.moviecarouselrecycler.CarouselRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import java.io.Serializable

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
class CarouselMovieItem(val item: Movie) : GenericItem(), Serializable {

    override val id: Int = item.id
    override val name: String? = item.title
    override val photoUrl: String? = item.posterPath
    override val info: String? = item.overview
    override val date: String? = item.releaseDate
    override var mediaType: String? = null

    override fun getRowHolderView(parent: ViewGroup, listener: IOnItemClickListener) : BaseRowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_item_carousel, parent, false)
        return CarouselRowHolder(view, listener)
    }

    init {
        mediaType = MoveeApplication.appContext.getString(R.string.movie_type)
    }

}