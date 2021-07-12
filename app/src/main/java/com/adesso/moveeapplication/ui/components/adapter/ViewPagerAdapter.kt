package com.adesso.moveeapplication.ui.components.adapter

import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adesso.moveeapplication.ui.screens.dashboard.tvseries.view.TvFragment
import com.adesso.moveeapplication.ui.screens.dashboard.movie.view.MovieFragment
import com.adesso.moveeapplication.ui.screens.dashboard.search.view.SearchFragment
import com.adesso.moveeapplication.ui.screens.dashboard.user.view.UserProfileFragment

/**
 * Created by Burak Karakoca on 6.10.2020.
 */
class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private var fragmentList: ArrayList<Fragment> = arrayListOf(
        MovieFragment(),
        TvFragment(),
        SearchFragment(),
        UserProfileFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}

