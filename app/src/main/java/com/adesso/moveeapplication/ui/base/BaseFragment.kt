package com.adesso.moveeapplication.ui.base

import android.renderscript.ScriptGroup
import android.widget.ImageView
import androidx.databinding.DataBindingComponent
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.components.generic.*
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel

open class BaseFragment: Fragment() {

    // TODO : parent activity'e ulaşmanın daha iyi bir yolu var mı?
    fun startLoading() {
        (activity as BaseView).startLoading()
    }

    fun stopLoading() {
        (activity as BaseView).stopLoading()
    }

    fun showBottomTabBar() {
        (activity as BaseView).showTabBar()
    }

    fun hideBottomTabBar() {
        (activity as BaseView).hideTabBar()
    }

    fun <T: ViewDataBinding> notifyDataBinding(binding : T) {
        binding.invalidateAll()
    }

    fun setIsInMainFragment(value: Boolean) {
        (activity as BaseView).isInMainFragment = value
    }

    fun getPrimarySectionList(sectionName: String, type: Int): PrimaryHeaderSection {
        return PrimaryHeaderSection(
            MoveeConstants.INT_ZERO,
            sectionName,
            type,
            BaseUIConstants.NONE_TYPE
        )
    }

    fun getSecondarySectionList(sectionName: String, type: Int): SecondaryHeaderSection {
        return SecondaryHeaderSection(
            MoveeConstants.INT_TWO,
            sectionName,
            type,
            BaseUIConstants.NONE_TYPE
        )
    }

    fun getRecyclerList(type: Int): List<RecyclerSection> {
        return listOf(
            RecyclerSection(
                MoveeConstants.INT_ONE,
                type,
                BaseUIConstants.NONE_TYPE
            )
        )
    }

    fun setHeartIcon(image: ImageView, isFavorite: Boolean) {
        if (isFavorite) {
            image.setImageResource(R.drawable.heart_selected)
        } else {
            image.setImageResource(R.drawable.heart)
        }
    }

    fun updateRateStars(starList: List<ImageView>, rate: Int?) {
        if (rate != null) {
            for (i in MoveeConstants.INT_ZERO until rate) {
                starList[i].setImageResource(R.drawable.rate_star_filled)
            }

            for (i in rate until MoveeConstants.INT_FIVE) {
                starList[i].setImageResource(R.drawable.rate_star)
            }
        }
    }

}