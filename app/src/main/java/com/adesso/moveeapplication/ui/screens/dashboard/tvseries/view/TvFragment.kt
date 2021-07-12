package com.adesso.moveeapplication.ui.screens.dashboard.tvseries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.util.ListJoinerUtil
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.*
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.components.util.StickHeaderItemDecoration
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainFragmentDirections
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.tvseries.viewmodel.TvViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.KeyboardUtil

class TvFragment : BaseFragment(), IOnItemClickListener {

    lateinit var tvViewModel : TvViewModel

    private lateinit var layoutManager: GridLayoutManager
    private lateinit var tvRecyclerAdapter: GenericRecyclerAdapter
    private lateinit var tvRecyclerView: RecyclerView
    private lateinit var totalItemList: List<GenericItem>

    private var genericTvSeriesList: List<TvSeriesItem> = ArrayList()
    private var tvHeaderList: List<GenericItem> = ArrayList()
    private var tvRecyclerList: List<RecyclerSection> = ArrayList()
    private var sectionInfo : List<Any> = ArrayList()

    private var tvSeriesPage = MoveeConstants.INT_ONE
    private var isPageLoading = false
    private var isPageStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(TvViewModel::class.java)

        registerLiveData()
        setUpLists()
        setUpUI()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            tvViewModel.favoriteList.value = TmdbUser.userFavorites

            if (!isPageStarted) {
                getData()
                isPageStarted = true
            }
        }
    }

    private fun getData() {
        tvViewModel.getTvGenreList()
        startLoading()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        // TODO : observe olayları kullandıktan sonra kaldır.

        registerGenre()
        registerTvTopRated()
        registerTvPopular()
        registerError()
    }

    private fun registerFavoriteList() {
        tvViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            tvRecyclerAdapter.notifyDataSetChanged()
            tvRecyclerView.invalidate()
        })
    }

    private fun registerGenre() {
        tvViewModel.tvGenreListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                MainViewModel.initGenreList(it.genres)
                tvViewModel.getTvTopRatedList(tvSeriesPage)
            } else {
                tvViewModel.getTvGenreList()
            }
        })
    }

    private fun registerTvTopRated() {
        tvViewModel.tvTopRatedListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                checkPagination(it)
            } else {
                tvViewModel.getTvTopRatedList(tvSeriesPage)
            }
        })
    }

    private fun registerTvPopular() {
        tvViewModel.tvPopularListResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                initPopularList(it.results)
                initRecyclerView()
                registerFavoriteList()
            } else {
                tvViewModel.getTvTopRatedList(tvSeriesPage)
            }

        })
    }

    private fun registerError() {
        tvViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    // TODO : Aşağıdaki fonksiyonlar generic yapılabilir.
    private fun setUpUI() {
        tvRecyclerView = requireView().findViewById(R.id.fragment_tv_movie_recyclerview)
        layoutManager = GridLayoutManager(MoveeApplication.appContext, MoveeConstants.INT_TWO)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == MoveeConstants.INT_ZERO
                    || position == MoveeConstants.INT_ONE
                    || position == MoveeConstants.INT_TWO) {
                    MoveeConstants.INT_TWO
                } else {
                    MoveeConstants.INT_ONE
                }
            }
        }
        tvRecyclerView.layoutManager = layoutManager
    }

    private fun initRecyclerView() {
        tvRecyclerAdapter = GenericRecyclerAdapter(totalItemList, this)
        tvRecyclerView.adapter = tvRecyclerAdapter
        tvRecyclerView.addItemDecoration(StickHeaderItemDecoration(tvRecyclerAdapter))

        stopLoading()
        listenRecyclerScroll()
    }

    private fun listenRecyclerScroll() {
        tvRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalPosition = layoutManager.itemCount
                val currentPosition = layoutManager.findLastVisibleItemPosition()

                val pageRateDouble = currentPosition.toDouble() / totalPosition.toDouble()

                if (pageRateDouble >= BaseUIConstants.PAGINATION_RATE && !isPageLoading) {
                    tvViewModel.getTvTopRatedList(tvSeriesPage)
                    isPageLoading = true
                }
            }
        })
    }
    // TODO : Generic yapmayı dene.
    private fun checkPagination(tvSeriesResponse: TvSeriesResponse) {
        if (tvSeriesPage != MoveeConstants.INT_ONE) {
            isPageLoading = false
            tvSeriesPage++
            val newTvList: List<TvSeriesItem> = ArrayList()
            tvSeriesResponse.results.forEach { tvSeries ->
                (newTvList as ArrayList).add(TvSeriesItem(tvSeries))
            }
            tvRecyclerAdapter.addItems(newTvList)
            stopLoading()
        } else {
            tvSeriesPage++
            initTotalList(tvSeriesResponse.results)
            tvViewModel.getTvPopularList()
        }
    }
    //endregion

    //region Data
    private fun setUpLists() {
        sectionInfo = listOf(
            MoveeApplication.appContext.getString(R.string.tv_series_string),
            MoveeApplication.appContext.getString(R.string.top_rated_string),
            BaseUIConstants.TV_SERIES_TYPE
        )
        val sections = setLists(sectionInfo)

        tvHeaderList = sections[MoveeConstants.INT_ZERO] as List<GenericItem>
        tvRecyclerList = sections[MoveeConstants.INT_ONE] as List<RecyclerSection>
    }

    private fun setLists(section: List<Any>) : List<Any> {

        val genericSectionList = listOf(
            getPrimarySectionList(
                section[MoveeConstants.INT_ZERO] as String,
                section[MoveeConstants.INT_TWO] as Int
            ),
            getSecondarySectionList(
                section[MoveeConstants.INT_ONE] as String,
                section[MoveeConstants.INT_TWO] as Int
            )
        )

        return listOf(
            genericSectionList,
            getRecyclerList(section[MoveeConstants.INT_TWO] as Int))

    }

    private fun initPopularList(tvPopular: List<TvSeries>) {
        tvPopular.forEach { tvSeries ->
            (MainViewModel.tvPopularList as ArrayList).add(CarouselTvSeriesItem(tvSeries))
        }
    }

    private fun initTotalList(tvSeries: List<TvSeries>) {
        tvSeries.forEach { series ->
            (genericTvSeriesList as ArrayList).add(TvSeriesItem(series))
        }

        totalItemList = ListJoinerUtil.concatenate(genericTvSeriesList)
        (totalItemList as ArrayList).add((tvHeaderList[0] as PrimaryHeaderSection).position, tvHeaderList[0])
        (totalItemList as ArrayList).add(tvRecyclerList[0].position, tvRecyclerList[0])
        (totalItemList as ArrayList).add((tvHeaderList[1] as SecondaryHeaderSection).position, tvHeaderList[1])
    }
    //endregion

    //region Events
    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        setIsInMainFragment(false)

        tvViewModel.selectedTvSeries.value = position
        val action =
            MainFragmentDirections.actionMainFragmentToTvSeriesDetailFragment(
                item as TvSeries
            )
        Navigation.findNavController(requireView()).navigate(action)
    }
    //endregion

}