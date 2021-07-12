package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.ui.components.generic.GenericItem

/**
 * Created by Burak Karakoca on 4.11.2020.
 */
object IteratorUtil {
    fun deleteById(list: ArrayList<GenericItem>, selectedId: Int) {
        val iterator = list.iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if(item.id == selectedId){
                iterator.remove()
            }
        }
    }
}