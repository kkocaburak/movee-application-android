package com.adesso.moveeapplication.ui.screens.dashboard.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbSessionSingleton
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseView
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.ViewPagerAdapter
import com.adesso.moveeapplication.ui.components.generic.*
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : BaseFragment() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var icons : List<Int>
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    private var isApplicationStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(MainViewModel::class.java)

        setUpUI()
        if (!isApplicationStarted) {
            registerLiveData()
            getUser()
            isApplicationStarted = true
        }
    }

    private fun getFavoriteMovies() {
        mainViewModel.getMovieFavoritesList()
    }

    private fun getFavoriteTvSeries() {
        mainViewModel.getTvFavoritesList()
    }

    private fun getRatedMovies() {
        mainViewModel.getRatedMovies()
    }

    private fun getRatedTvSeries() {
        mainViewModel.getRatedTvSeries()
    }

    private fun getUser() {
        mainViewModel.getUserInfo()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerUserInfo()
        registerFavorites()
        registerRated()
        registerError()
    }

    private fun registerUserInfo() {
        mainViewModel.userInfoResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                TmdbUser.user = it

                if (!TmdbSessionSingleton.isGuest) {
                    TmdbUser.userFavorites = arrayListOf()

                    getFavoriteMovies()
                    getFavoriteTvSeries()
                    getRatedMovies()
                    getRatedTvSeries()
                }

            }
        })
    }

    private fun registerRated() {
        mainViewModel.userRatedMoviesResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                TmdbUser.userRatedMovies = it.results as ArrayList<Movie>
            } else {
                getRatedMovies()
            }
        })

        mainViewModel.userRatedTvSeriesResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                TmdbUser.userRatedTvSeries = it.results as ArrayList<TvSeries>
            } else {
                getRatedTvSeries()
            }
        })
    }

    private fun registerFavorites() {
        mainViewModel.userFavoriteMoviesResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                initFavoriteItems(it)
            } else {
                getFavoriteMovies()
            }
        })

        mainViewModel.userFavoriteTvSeriesResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                initFavoriteItems(it)
            } else {
                getFavoriteTvSeries()
            }
        })
    }

    private fun registerError() {
        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    private fun initFavoriteItems(response: Any) {
        val favoriteItems : ArrayList<GenericItem> = arrayListOf()

        if (response is MovieResponse) {
            response.results.forEach { movie ->
                favoriteItems.add(FavoriteMovieItem(movie))
            }
        } else if (response is TvSeriesResponse) {
            response.results.forEach { tvSeries ->
                favoriteItems.add(FavoriteTvItem(tvSeries))
            }
        }

        favoriteItems.forEach{
            TmdbUser.userFavorites!!.add(it)
        }
    }

    private fun setUpUI() {
        icons = listOf(
            R.drawable.ic_tabbar_movie_selected,
            R.drawable.ic_tabbar_serie,
            R.drawable.ic_tabbar_search,
            R.drawable.ic_tabbar_profil
        )

        tabLayout = (activity as BaseView).tabLayout
        viewPager = requireView().findViewById(R.id.fragment_main_viewPager)

        val viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        viewPager.isUserInputEnabled = false
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.icon = ResourcesCompat.getDrawable(
                MoveeApplication.appContext.resources,
                icons[position],
                MoveeApplication.appContext.theme
            )
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        setIsInMainFragment(true)
    }

}