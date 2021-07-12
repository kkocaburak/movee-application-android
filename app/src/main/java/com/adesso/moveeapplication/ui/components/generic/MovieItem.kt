package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerMovieRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import java.io.Serializable

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
class MovieItem(override val item: Movie) : GenericItem(), Serializable, IMovieItem {

    override val id: Int? = item.id
    override val name: String? = item.title
    override val photoUrl: String? = item.posterPath
    override val info: String? = item.overview
    override val date: String? = item.releaseDate
    override var mediaType: String? = null

    override fun getRowHolderView(parent: ViewGroup, listener: IOnItemClickListener) : BaseRowHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                R.layout.recycler_item_movie,
                parent,
                false
            )
        return RecyclerMovieRowHolder(
            binding,
            listener
        )
    }

    init {
        mediaType = MoveeApplication.appContext.getString(R.string.movie_type)
    }

}