package com.adesso.moveeapplication.ui.components.favorites

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.components.generic.*
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 3.11.2020.
 */
class RecyclerFavoritesRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var selectedItem: Any
    private var listener = _listener

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, selectedItem, RecyclerViewTypes.MOVIE_ITEM.value)
        }
    }

    override fun bindModel(model: Any) {
        if ((model as GenericItem).mediaType == MoveeApplication.appContext.getString(R.string.movie_type)) {
            selectedItem = (model as IMovieItem).item
            binding.setVariable(BR.favoriteItem, model as IMovieItem)
        } else if (model.mediaType == MoveeApplication.appContext.getString(R.string.tv_series_type)) {
            selectedItem = (model as ITvSeriesItem).item
            binding.setVariable(BR.favoriteItem, model as ITvSeriesItem)
        }
    }

}