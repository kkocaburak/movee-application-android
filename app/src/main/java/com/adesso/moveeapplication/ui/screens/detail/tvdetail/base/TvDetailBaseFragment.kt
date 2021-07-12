package com.adesso.moveeapplication.ui.screens.detail.tvdetail.base

import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.screens.detail.tvdetail.viewmodel.TvSeriesDetailViewModel

/**
 * Created by Burak Karakoca on 9.10.2020.
 */
abstract class TvDetailBaseFragment : BaseFragment() {
    lateinit var tvSeriesDetailViewModel : TvSeriesDetailViewModel
}