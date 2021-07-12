package com.adesso.moveeapplication.ui.components.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.generic.PrimaryHeaderSection
import com.adesso.moveeapplication.ui.components.movierecycler.MovieRecyclerConstants
import com.adesso.moveeapplication.ui.components.movierecycler.RecyclerViewTypes
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.components.util.IStickyHeader
import com.adesso.moveeapplication.ui.screens.dashboard.movie.view.MovieFragment

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
class GenericRecyclerAdapter(
    private val totalItemList : List<GenericItem>,
    val listener : IOnItemClickListener
) : RecyclerView.Adapter<BaseRowHolder>(), IStickyHeader {

    var secondaryText : String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRowHolder {
        return totalItemList[viewType].getRowHolderView(parent, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return totalItemList.size
    }

    override fun onBindViewHolder(holder: BaseRowHolder, position: Int) {
        holder.bindModel(totalItemList[position])
    }

    fun addItems(genericItems: List<GenericItem>) {
        (totalItemList as ArrayList).addAll(genericItems)
        notifyDataSetChanged()
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        var itPos = itemPosition
        do {
            if (this.isHeader(itPos)) {
                headerPosition = itPos
                break
            }
            itPos -= 1
        } while (itPos >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return if (getItemViewType(headerPosition) == RecyclerViewTypes.PRIMARY_HEADER_SECTION.value) {
            R.layout.recycler_item_main_header
        } else {
            R.layout.recycler_item_section_row
        }
    }

    override fun bindHeaderData(header: View?, headerPosition: Int) {
        val headerTextView = header!!.findViewById<TextView>(R.id.primary_header_textview)
        val mapImage = header.findViewById<LinearLayout>(R.id.primary_header_icon_map)

        if (headerPosition == RecyclerViewTypes.PRIMARY_HEADER_SECTION.value) {
            if ((totalItemList[headerPosition] as PrimaryHeaderSection).type == BaseUIConstants.MOVIE_TYPE) {
                headerTextView!!.text = (totalItemList[headerPosition] as PrimaryHeaderSection).title
                secondaryText = MoveeApplication.appContext.getString(R.string.popular_movies_string)
            } else if ((totalItemList[headerPosition] as PrimaryHeaderSection).type == BaseUIConstants.TV_SERIES_TYPE) {
                headerTextView!!.text = (totalItemList[headerPosition] as PrimaryHeaderSection).title
                mapImage.visibility = View.INVISIBLE
                secondaryText = MoveeApplication.appContext.getString(R.string.top_rated_string)
            }

        } else if (headerPosition == RecyclerViewTypes.SECONDARY_HEADER_SECTION.value) {
            setUpHeaderText(headerTextView, secondaryText!!)
        }

    }

    override fun isHeader(itemPosition: Int): Boolean {
        for (section in MovieFragment.sectionList) {
            if (section.position == itemPosition) {
                    return true
            }
        }
        return false
    }

    private fun setUpHeaderText(m_headerTextView: TextView, headerText: String) {
        m_headerTextView.text = headerText
        m_headerTextView.setTextColor(Color.WHITE)
        m_headerTextView.textSize = MovieRecyclerConstants.STICKY_HEADER_TEXT_SIZE
        m_headerTextView.gravity = MovieRecyclerConstants.STICKY_HEADER_GRAVITY
        m_headerTextView.setBackgroundColor(
            MoveeApplication.appContext.getColor(R.color.vibrant_blue)
        )
        m_headerTextView.setPadding(
            MovieRecyclerConstants.STICKY_HEADER_PADDING_LEFT,
            MovieRecyclerConstants.STICKY_HEADER_PADDING_TOP,
            m_headerTextView.paddingRight,
            m_headerTextView.paddingBottom
        )
        val params: LinearLayout.LayoutParams =
            m_headerTextView.layoutParams as LinearLayout.LayoutParams
        params.height = MovieRecyclerConstants.STICKY_HEADER_HEIGHT
        m_headerTextView.layoutParams = params
    }

}