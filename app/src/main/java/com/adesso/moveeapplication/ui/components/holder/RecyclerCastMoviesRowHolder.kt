package com.adesso.moveeapplication.ui.components.holder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.CastMoviesItem
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 13.10.2020.
 */
class RecyclerCastMoviesRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var castInfo: Movie
    private var listener = _listener

    init {
        itemView.setOnClickListener(this)
    }

    override fun bindModel(model: Any) {
        castInfo = (model as CastMoviesItem).item
        binding.setVariable(BR.castMovie, castInfo)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, castInfo, RecyclerViewTypes.CAST.value)
        }
    }

}