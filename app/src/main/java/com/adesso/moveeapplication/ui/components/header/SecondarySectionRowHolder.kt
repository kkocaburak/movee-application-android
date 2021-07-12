package com.adesso.moveeapplication.ui.components.header

import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.generic.SecondaryHeaderSection

/**
 * Created by Burak Karakoca on 12.10.2020.
 */

class SecondarySectionRowHolder(private val binding: ViewDataBinding)
    : BaseRowHolder(binding.root) {

    override fun bindModel(model: Any) {
        binding.setVariable(BR.secondaryHeader, model as SecondaryHeaderSection)
    }

}