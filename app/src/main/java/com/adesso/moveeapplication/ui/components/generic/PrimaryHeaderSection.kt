package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.header.PrimarySectionRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 25.09.2020.
 */
data class PrimaryHeaderSection(
    override val position: Int,
    override val title: String,
    override val type: Int,
    override val id: Int
) : GenericItem(), IHeaderModel {

    override val name: String? = null
    override val photoUrl: String? = null
    override val info: String? = null
    override val date: String? = null
    override var mediaType: String? = null

    override fun getRowHolderView(
        parent: ViewGroup,
        listener: IOnItemClickListener
    ): BaseRowHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                R.layout.recycler_item_main_header,
                parent,
                false
            )

        return PrimarySectionRowHolder(
            binding,
            listener
        )
    }

}