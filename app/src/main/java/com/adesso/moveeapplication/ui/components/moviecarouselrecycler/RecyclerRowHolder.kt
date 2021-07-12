package com.adesso.moveeapplication.ui.components.moviecarouselrecycler

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel

/**
 * Created by Burak Karakoca on 29.09.2020.
 */
class RecyclerRowHolder(itemView: View, _listener: IOnItemClickListener, _type: Int?) : BaseRowHolder(itemView) {
    private var movieRecyclerView: RecyclerView? = null
    private var listener = _listener
    private var type = _type
    private lateinit var carouselItems : List<GenericItem>

    override fun bindModel(model: Any) {

        carouselItems = if (type == MoveeConstants.INT_ZERO) {
            MainViewModel.movieVisionList
        } else {
            MainViewModel.tvPopularList
        }

        movieRecyclerView = itemView.findViewById(R.id.recyclerview_carousel)
        val mLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        movieRecyclerView!!.layoutManager = mLayoutManager
        val movieRecyclerViewAdapter =
            GenericRecyclerAdapter(
                carouselItems,
                listener
            )
        movieRecyclerView!!.adapter = movieRecyclerViewAdapter
    }
}