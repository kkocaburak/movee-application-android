package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.model.tmdb.ITvSeries
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.components.tvrecycler.RecyclerTvRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import java.io.Serializable

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
class TvSeriesItem(override val item: TvSeries) : GenericItem(), Serializable, ITvSeriesItem {

    override val id: Int = item.id
    override val name: String? = item.name
    override val photoUrl: String? = item.posterPath
    override val info: String? = item.overview
    override val date: String? = item.firstAirDate
    override var mediaType: String? = null

    override fun getRowHolderView(
        parent: ViewGroup,
        listener: IOnItemClickListener
    ): BaseRowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                R.layout.recycler_item_tv_series,
                parent,
                false
            )
        return RecyclerTvRowHolder(binding, listener)
    }

    init {
        mediaType = MoveeApplication.appContext.getString(R.string.tv_series_type)
    }

}