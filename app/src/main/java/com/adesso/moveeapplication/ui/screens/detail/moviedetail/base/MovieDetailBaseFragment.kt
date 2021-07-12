package com.adesso.moveeapplication.ui.screens.detail.moviedetail.base

import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.screens.detail.moviedetail.viewmodel.MovieDetailViewModel

/**
 * Created by Burak Karakoca on 5.10.2020.
 */
abstract class MovieDetailBaseFragment : BaseFragment() {
    lateinit var movieDetailViewModel : MovieDetailViewModel
}