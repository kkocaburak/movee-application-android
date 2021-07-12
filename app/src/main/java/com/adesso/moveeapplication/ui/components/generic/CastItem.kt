package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.model.tmdb.Cast
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.holder.RecyclerCastRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import java.io.Serializable

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
class CastItem(override val item: Cast) : GenericItem(), Serializable, ICastItem {

    override val id: Int = item.id
    override val name: String? = item.name
    override val photoUrl: String? = item.profilePath
    override val info: String? = item.character
    override val date: String? = null
    override var mediaType: String? = MoveeApplication.appContext.getString(R.string.cast_string)

    override fun getRowHolderView(
        parent: ViewGroup,
        listener: IOnItemClickListener
    ): BaseRowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                R.layout.recycler_item_cast,
                parent,
                false
            )
        return RecyclerCastRowHolder(binding, listener)
    }

}