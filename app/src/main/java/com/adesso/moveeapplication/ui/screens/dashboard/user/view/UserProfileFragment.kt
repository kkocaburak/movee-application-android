package com.adesso.moveeapplication.ui.screens.dashboard.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.BR
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbSessionSingleton
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.ProfileResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.databinding.FragmentMovieDetailBinding
import com.adesso.moveeapplication.databinding.FragmentUserProfileBinding
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.generic.MovieItem
import com.adesso.moveeapplication.ui.components.generic.SecondaryHeaderSection
import com.adesso.moveeapplication.ui.components.generic.TvSeriesItem
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.components.util.StickHeaderItemDecoration
import com.adesso.moveeapplication.ui.screens.dashboard.login.view.LoginFragmentDirections
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainFragmentDirections
import com.adesso.moveeapplication.ui.screens.dashboard.user.viewmodel.UserProfileViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.KeyboardUtil

class UserProfileFragment : BaseFragment(), IOnItemClickListener {

    private lateinit var userProfileViewModel: UserProfileViewModel

    private lateinit var usernameText: TextView
    private lateinit var favoriteRecycler: RecyclerView

    private lateinit var favoriteRecyclerAdapter : GenericRecyclerAdapter

    private var userProfileBinding: FragmentUserProfileBinding? = null

    var isGuest = TmdbSessionSingleton.isGuest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileBinding = FragmentUserProfileBinding.bind(view)

        userProfileViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(UserProfileViewModel::class.java)

        stopLoading()
        initComponents()
        initScreen()
        setUpBinding()

        if(!isGuest) {
            initFavoriteList()
            registerLiveData()
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            showBottomTabBar()
            userProfileViewModel.favoriteList.value = TmdbUser.userFavorites
        }
    }

    private fun setUpBinding() {
        userProfileBinding?.userProfileFragment = this
    }

    private fun registerLiveData() {
        registerFavoriteList()
    }

    private fun registerFavoriteList() {
        userProfileViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            favoriteRecyclerAdapter.notifyDataSetChanged()
            favoriteRecycler.invalidate()
        })
    }

    //region UI
    private fun initComponents() {
        usernameText = requireView().findViewById(R.id.fragment_user_profile_txt_name)
        favoriteRecycler = requireView().findViewById(R.id.fragment_user_profile_recycler_favorites)
    }

    private fun initFavoriteList() {
        val layoutManager = LinearLayoutManager(MoveeApplication.appContext)
        favoriteRecycler.layoutManager = layoutManager

        val favoriteSection = SecondaryHeaderSection(
            MoveeConstants.INT_ZERO,
            getString(R.string.favorites_string),
            BaseUIConstants.NONE_TYPE,
            BaseUIConstants.NONE_TYPE
        )

        TmdbUser.userFavorites!!.add(MoveeConstants.INT_ZERO, favoriteSection)

        favoriteRecyclerAdapter = GenericRecyclerAdapter(
                TmdbUser.userFavorites!!,
                this
            )
        favoriteRecycler.adapter = favoriteRecyclerAdapter
    }

    private fun initScreen() {
        if (TmdbUser.user == null) {
            usernameText.text = getString(R.string.guest_string)
            return
        }

        val user = TmdbUser.user!!

        if (!user.name.isNullOrBlank()) {
            user.username?.let {
                usernameText.text = user.username
            }
        } else if (!user.username.isNullOrBlank()) {
            usernameText.text = user.username
        }

    }

    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        val action = if (item is Movie) {
            MainFragmentDirections.actionMainFragmentToMovieInfo(item)
        } else {
            MainFragmentDirections.actionMainFragmentToTvSeriesDetailFragment(item as TvSeries)
        }

        Navigation.findNavController(requireView()).navigate(action)
        setIsInMainFragment(false)
    }

    fun goToSettingsScreen() {
        val action =
            MainFragmentDirections.actionMainFragmentToSettingsFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }
    //endregion

}