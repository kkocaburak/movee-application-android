package com.adesso.moveeapplication.ui.screens.dashboard.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbSessionSingleton
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.util.ListJoinerUtil
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.*
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.components.util.StickHeaderItemDecoration
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainFragmentDirections
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.movie.viewmodel.MovieViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.KeyboardUtil

class MovieFragment : BaseFragment(), IOnItemClickListener {

    lateinit var movieViewModel: MovieViewModel

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var movieRecyclerAdapter: GenericRecyclerAdapter
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var totalItemList: List<GenericItem>

    private var genericMovieList: List<MovieItem> = ArrayList()
    private var recyclerList: List<RecyclerSection> = ArrayList()

    private var moviePage = MoveeConstants.INT_ONE

    private var isPageLoading = false

    companion object {
        var sectionList: List<IHeaderModel> = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(MovieViewModel::class.java)

        registerLiveData()
        setUpUI()
        setLists()
        getData()
    }

    private fun getData() {
        movieViewModel.getMovieGenreList()
        startLoading()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            movieViewModel.favoriteList.value = TmdbUser.userFavorites
        }
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerMovieGenre()
        registerMovie()
        registerMovieVision()
        registerError()
    }

    private fun registerFavoriteList() {
        movieViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            movieRecyclerAdapter.notifyDataSetChanged()
            movieRecyclerView.invalidate()
        })
    }

    private fun registerMovieGenre() {
        movieViewModel.movieGenreListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                MainViewModel.initGenreList(it.genres)
                movieViewModel.getMovieList(moviePage)
            } else {
                movieViewModel.getMovieGenreList()
            }
        })
    }

    private fun registerMovie() {
        movieViewModel.movieListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (moviePage != MoveeConstants.INT_ONE) {
                    isPageLoading = false
                    moviePage++
                    val newMovieList: List<MovieItem> = ArrayList()
                    it.results.forEach { movie ->
                        (newMovieList as ArrayList).add(MovieItem(movie))
                    }
                    movieRecyclerAdapter.addItems(newMovieList)
                    stopLoading()
                } else {
                    moviePage++
                    initTotalList(it.results)
                    movieViewModel.getMovieVisionList()
                }
            } else {
                movieViewModel.getMovieList(moviePage)
            }
        })
    }

    private fun registerMovieVision() {
        movieViewModel.movieVisionListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                it.results.forEach { movie ->
                    (MainViewModel.movieVisionList as ArrayList).add(CarouselMovieItem(movie))
                }
                initRecyclerView()
                registerFavoriteList()
            } else {
                movieViewModel.getMovieVisionList()
            }
        })
    }

    private fun registerError() {
        movieViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    private fun setUpUI() {
        movieRecyclerView = requireView().findViewById(R.id.fragment_movie_movie_recyclerview)
        layoutManager = LinearLayoutManager(MoveeApplication.appContext)
        movieRecyclerView.layoutManager = layoutManager

        showBottomTabBar()
    }

    private fun initRecyclerView () {
        movieRecyclerAdapter =
            GenericRecyclerAdapter(
                totalItemList,
                this
            )
        movieRecyclerView.adapter = movieRecyclerAdapter
        movieRecyclerView.addItemDecoration(
            StickHeaderItemDecoration(movieRecyclerAdapter)
        )

        stopLoading()
        listenRecyclerScroll()
    }

    private fun listenRecyclerScroll() {
        movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalPosition = layoutManager.itemCount
                val currentPosition = layoutManager.findLastVisibleItemPosition()

                val pageRateDouble = currentPosition.toDouble() / totalPosition.toDouble()

                if (pageRateDouble >= BaseUIConstants.PAGINATION_RATE && !isPageLoading) {
                    movieViewModel.getMovieList(moviePage)
                    isPageLoading = true
                }
            }
        })
    }
    //endregion

    //region Data
    private fun setLists() {
        sectionList = listOf(
            getPrimarySectionList(
                MoveeApplication.appContext.getString(R.string.movies_string),
                BaseUIConstants.MOVIE_TYPE
            ),
            getSecondarySectionList(
                MoveeApplication.appContext.getString(R.string.activity_main_text_popular),
                BaseUIConstants.MOVIE_TYPE
            )
        )
        recyclerList = getRecyclerList(BaseUIConstants.MOVIE_TYPE)
    }

    private fun initTotalList(movies: List<Movie>) {

        movies.forEach { movie ->
            (genericMovieList as ArrayList).add(MovieItem(movie))
        }

        totalItemList = ListJoinerUtil.concatenate(genericMovieList)
        (totalItemList as ArrayList).add(sectionList[0].position, sectionList[0] as PrimaryHeaderSection)
        (totalItemList as ArrayList).add(recyclerList[0].position, recyclerList[0])
        (totalItemList as ArrayList).add(sectionList[1].position, sectionList[1] as SecondaryHeaderSection)

    }
    //endregion

    //region Events
    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        setIsInMainFragment(false)
        val action : NavDirections = if (item is Movie) {
            MainFragmentDirections.actionMainFragmentToMovieInfo(item)
        } else {
            MainFragmentDirections.actionMainFragmentToMapFragment()
        }

        Navigation.findNavController(requireView()).navigate(action)

    }
    //endregion

}