package com.adesso.moveeapplication.ui.components.holder

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.data.model.tmdb.ICast
import com.adesso.moveeapplication.ui.base.ImageSizeEnum
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.ICastItem
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.util.GlideUtil
import com.adesso.moveeapplication.ui.util.StringCompilerUtil

/**
 * Created by Burak Karakoca on 10.10.2020.
 */
class RecyclerCastRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var castInfo: ICast
    private var listener = _listener

    init {
        itemView.setOnClickListener(this)
    }

    override fun bindModel(model: Any) {
        castInfo = (model as ICastItem).item
        binding.setVariable(BR.cast, castInfo)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position, castInfo, RecyclerViewTypes.CAST.value)
        }
    }

}

@BindingAdapter("android:src") // TODO : An annotation argument must be a compile-time constant
fun getImage(view: ImageView, url: String?) {
    if (url != null) {
        GlideUtil.setImage(
            StringCompilerUtil.compileString(ImageSizeEnum.W_185.size, url),
            view
        )
    }
}