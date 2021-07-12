package com.adesso.moveeapplication.ui.components.generic

/**
 * Created by Burak Karakoca on 3.11.2020.
 */
interface IGenericItem {
    val id: Int?
    val name: String?
    val photoUrl: String?
    val info: String?
    val date: String?
    var mediaType: String?
}