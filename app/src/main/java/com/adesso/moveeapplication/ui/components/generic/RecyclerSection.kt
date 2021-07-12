package com.adesso.moveeapplication.ui.components.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.moviecarouselrecycler.RecyclerRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 29.09.2020.
 */
data class RecyclerSection(val position: Int, val type: Int, override val id: Int) : GenericItem() {

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
        val view = inflater.inflate(R.layout.recycler_item_recycler_carousel, parent, false)
        return RecyclerRowHolder(
            view,
            listener,
            type
        )
    }

}