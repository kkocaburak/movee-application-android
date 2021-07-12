package com.adesso.moveeapplication.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Burak Karakoca on 25.09.2020.
 */
abstract class BaseRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindModel(model: Any)
}