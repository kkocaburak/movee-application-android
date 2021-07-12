package com.adesso.moveeapplication.ui.components.header

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.components.generic.PrimaryHeaderSection
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener

/**
 * Created by Burak Karakoca on 28.09.2020.
 */
class PrimarySectionRowHolder(
    private val binding: ViewDataBinding,
    _listener: IOnItemClickListener
) : BaseRowHolder(binding.root), View.OnClickListener {

    private lateinit var layoutHeader : LinearLayout
    private lateinit var item : PrimaryHeaderSection
    private val listener = _listener

    init {
        itemView.setOnClickListener(this)
    }

    override fun bindModel(model: Any) {
        item = model as PrimaryHeaderSection

        binding.setVariable(BR.primaryHeader, model)

        layoutHeader = itemView.findViewById(R.id.primary_header_icon_map)
        layoutHeader.setOnClickListener {
            openMap()
        }
    }

    override fun onClick(v: View?) {}

    private fun openMap() {
        listener.onItemClick(BaseUIConstants.NONE_TYPE, item, BaseUIConstants.NONE_TYPE)
    }

}