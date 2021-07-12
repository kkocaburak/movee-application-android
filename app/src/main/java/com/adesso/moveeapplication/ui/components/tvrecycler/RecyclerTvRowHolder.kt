package com.adesso.moveeapplication.ui.components.tvrecycler

import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.model.tmdb.ITvSeries
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.ITvSeriesItem
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.util.TmdbUserUtil
import com.adesso.moveeapplication.ui.util.VisibilityUtil

/**
 * Created by Burak Karakoca on 7.10.2020.
 */
class RecyclerTvRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var tvSeriesInfo: ITvSeries
    private var listener = _listener
    private val tvLikeButton =
        itemView.findViewById<ImageView>(R.id.recycler_item_tv_btn_like)

    init {
        itemView.setOnClickListener(this)
    }

    override fun bindModel(model: Any) {
        tvSeriesInfo = (model as ITvSeriesItem).item
        binding.setVariable(BR.tvSeries, tvSeriesInfo)
        VisibilityUtil.setVisibilityForValue(
            tvLikeButton,
            TmdbUserUtil.isItemFavorite(tvSeriesInfo.id)
        )
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, tvSeriesInfo, RecyclerViewTypes.TV_ITEM.value)
        }
    }

}