package com.adesso.moveeapplication.ui.components.moviecarouselrecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.base.ImageSizeEnum
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.CarouselMovieItem
import com.adesso.moveeapplication.ui.components.generic.CarouselTvSeriesItem
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.util.GenreUtil
import com.adesso.moveeapplication.ui.util.GlideUtil
import com.adesso.moveeapplication.ui.util.StringCompilerUtil

/**
 * Created by Burak Karakoca on 29.09.2020.
 */
class CarouselRowHolder(itemView: View, _listener: IOnItemClickListener)
    : BaseRowHolder(itemView), View.OnClickListener {
    private lateinit var itemInfo: Any
    private var listener = _listener

    private val movieText = itemView.findViewById<TextView>(R.id.recyclerview_vision_txt_movie_name)
    private val movieGenreText =
        itemView.findViewById<TextView>(R.id.recyclerview_vision_txt_movie_genre)
    private val movieImage = itemView.findViewById<ImageView>(R.id.recycler_item_tv_img)
    private val movieRatingText =
        itemView.findViewById<TextView>(R.id.recyclerview_vision_txt_movie_rate)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, itemInfo, RecyclerViewTypes.CAROUSEL_ITEM.value)
        }
    }

    override fun bindModel(model: Any) {
        if (model is CarouselMovieItem) {
            itemInfo = model.item
            initMovieRow(itemInfo as Movie)
        } else {
            itemInfo = (model as CarouselTvSeriesItem).item
            initTvRow(itemInfo as TvSeries)
        }
    }

    private fun initMovieRow(movieItem: Movie) {
        movieText.text = movieItem.title
        movieGenreText.text = GenreUtil.getGenre(movieItem.genreIds)
        movieRatingText.text = movieItem.voteAverage.toString()

        GlideUtil.setImage(
            StringCompilerUtil.compileString(ImageSizeEnum.W_342.size, movieItem.posterPath!!),
            movieImage
        )
    }

    private fun initTvRow(tvItem: TvSeries) {
        movieText.text = tvItem.name
        movieGenreText.text = GenreUtil.getGenre(tvItem.genreIds)
        movieRatingText.text = tvItem.voteAverage.toString()

        GlideUtil.setImage(
            StringCompilerUtil.compileString(ImageSizeEnum.W_342.size, tvItem.posterPath),
            movieImage
        )
    }

}