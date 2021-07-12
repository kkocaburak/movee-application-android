package com.adesso.moveeapplication.ui.components.generic

import android.view.View
import android.view.ViewGroup
import com.adesso.moveeapplication.ui.base.BaseRowHolder
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import java.io.Serializable

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
abstract class GenericItem : Serializable, IGenericItem {
    abstract fun getRowHolderView(parent: ViewGroup, listener: IOnItemClickListener) : BaseRowHolder

}