package com.adesso.moveeapplication.ui.components.movierecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.model.tmdb.IMovie
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.IMovieItem
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.util.GenreUtil
import com.adesso.moveeapplication.ui.util.TmdbUserUtil
import com.adesso.moveeapplication.ui.util.VisibilityUtil

/**
 * Created by Burak Karakoca on 28.09.2020.
 */
class RecyclerMovieRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var movieInfo: IMovie
    private var listener = _listener

    private val movieGenreText =
        itemView.findViewById<TextView>(R.id.recycler_item_movie_txt_genre)
    private val movieLikeButton =
        itemView.findViewById<ImageView>(R.id.recycler_item_movie_btn_like)

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, movieInfo, RecyclerViewTypes.MOVIE_ITEM.value)
        }
    }

    override fun bindModel(model: Any) {

        movieInfo = (model as IMovieItem).item
        binding.setVariable(BR.movie, model.item)

        if (movieInfo.genreIds != null) {
            movieGenreText.text = GenreUtil.getGenre(movieInfo.genreIds!!)
        }

        VisibilityUtil.setVisibilityForValue(
            movieLikeButton,
            TmdbUserUtil.isItemFavorite(movieInfo.id)
        )
    }

}

