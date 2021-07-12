package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.model.tmdb.SearchResponse
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.searchrecycler.RecyclerSearchRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
class SearchItem(override val item: SearchResponse): GenericItem(), IMovieItem, ITvSeriesItem, ICastItem {

    override val id: Int = item.id
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
                R.layout.recycler_item_search,
                parent,
                false
            )
        return RecyclerSearchRowHolder(binding, listener)
    }
}