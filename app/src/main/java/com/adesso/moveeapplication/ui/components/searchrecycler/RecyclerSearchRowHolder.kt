package com.adesso.moveeapplication.ui.components.searchrecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.model.tmdb.IMovie
import com.adesso.moveeapplication.data.model.tmdb.ITvSeries
import com.adesso.moveeapplication.data.model.tmdb.SearchResponse
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.SearchItem
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.dashboard.search.base.SearchType
import com.adesso.moveeapplication.ui.util.GenreUtil
import com.adesso.moveeapplication.ui.util.StringBuilderUtil
import java.lang.StringBuilder

/**
 * Created by Burak Karakoca on 19.10.2020.
 */

class RecyclerSearchRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var item: Any
    private var listener = _listener

    private val itemInfo = itemView.findViewById<TextView>(R.id.recycler_item_search_txt_item_info)
    private val itemIcon = itemView.findViewById<ImageView>(R.id.recycler_item_search_ic_item)
    private val itemTypeName = itemView.findViewById<TextView>(R.id.recycler_item_search_txt_item)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, item, RecyclerViewTypes.MOVIE_ITEM.value)
        }
    }

    override fun bindModel(model: Any) {
        item = model as SearchItem
        binding.setVariable(BR.searchItem, item)

        when (model.item.mediaType) {
            SearchType.MOVIE.type -> {
                bindMovie(model.item as IMovie)
            }
            SearchType.TV_SERIES.type -> {
                bindTvSeries(model.item as ITvSeries)
            }
            else -> {
                bindCast(model.item)
            }
        }

    }

    private fun bindMovie(movie : IMovie) {
        if (movie.genreIds != null) {
            itemInfo.text = GenreUtil.getGenre(movie.genreIds!!)
        }
        itemIcon.setImageResource(R.drawable.ic_tabbar_movie_selected)
        itemTypeName.text = MoveeApplication.appContext.getString(R.string.movie_string)
    }

    private fun bindTvSeries(tvSeries : ITvSeries) {
        itemInfo.text = GenreUtil.getGenre(tvSeries.genreIds!!)
        itemIcon.setImageResource(R.drawable.ic_tabbar_serie_selected)
        itemTypeName.text = MoveeApplication.appContext.getString(R.string.tv_series_string)
    }

    private fun bindCast(cast : SearchResponse) {
        val stringBuilder = StringBuilder()
        cast.knownFor.forEach {
            StringBuilderUtil.checkStringBuilder(stringBuilder)
            if (it.mediaType == SearchType.MOVIE.type) {
                stringBuilder.append(it.title)
            } else {
                stringBuilder.append(it.name)
            }
        }
        itemInfo.text = stringBuilder.toString()

        itemIcon.setImageResource(R.drawable.ic_tabbar_profil_selected)
        itemTypeName.text = MoveeApplication.appContext.getString(R.string.cast_string)
    }

}