package com.adesso.moveeapplication.ui.screens.detail.tvdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbSessionSingleton
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesDetailResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.util.ListJoinerUtil
import com.adesso.moveeapplication.data.model.tmdb.*
import com.adesso.moveeapplication.databinding.FragmentTvSeriesDetailBinding
import com.adesso.moveeapplication.ui.base.ImageSizeEnum
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.generic.CastItem
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.FavoriteTvItem
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.detail.base.PostFavoriteItemType
import com.adesso.moveeapplication.ui.screens.detail.tvdetail.base.TvDetailBaseFragment
import com.adesso.moveeapplication.ui.screens.detail.tvdetail.viewmodel.TvSeriesDetailViewModel
import com.adesso.moveeapplication.ui.util.*

class TvSeriesDetailFragment : TvDetailBaseFragment(), IOnItemClickListener {

    lateinit var selectedTvSeries: ITvSeries
    private lateinit var tvCastList: List<Cast>
    private lateinit var tvDetail: TvSeriesDetailResponse
    private lateinit var totalItemList: List<GenericItem>
    private lateinit var tvSeriesCastRecyclerview: RecyclerView
    private var genericCastList: List<CastItem> = ArrayList()

    private lateinit var tvSeriesImage: ImageView
    private lateinit var tvSeriesRateList: List<ImageView>

    private var isTvSeriesRated = false
    private var rateValue : Int? = null

    private var tvDetailBinding: FragmentTvSeriesDetailBinding? = null

    var genreString : String? = null
    var tvRateString : String? = null

    var isRateClicked = false
    var isItemFavorite = false
    var isGuest = TmdbSessionSingleton.isGuest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            selectedTvSeries = TvSeriesDetailFragmentArgs.fromBundle(
                it
            ).tvDetail
        }

        return inflater.inflate(R.layout.fragment_tv_series_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDetailBinding = FragmentTvSeriesDetailBinding.bind(view)
        tvDetailBinding?.lifecycleOwner = this

        setUpViewModel()
        registerLiveData()
        setUpUI()
        initComponents()
        initRateViewList()
        getTvDetailData()
        checkForGuest()
    }

    private fun setUpViewModel() {
        tvSeriesDetailViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(TvSeriesDetailViewModel::class.java)
    }

    private fun setUpDataBinding() {
        tvDetailBinding?.tvDetailView = this
    }

    private fun checkForGuest() {
        if(!isGuest) {
            isTvSeriesRated = TmdbUserUtil.isTvSeriesRated(selectedTvSeries.id)
            getTvSeriesRate()
        }
    }

    private fun getTvSeriesRate() {
        tvRateString = getString(
            R.string.rate_string,
            TmdbUserUtil.getRatedTvSeriesRateValue(selectedTvSeries.id).toString()
        )
        updateRateStars(tvSeriesRateList, TmdbUserUtil.getRatedTvSeriesRateValue(selectedTvSeries.id))
        notifyDataBinding(tvDetailBinding as ViewDataBinding)
    }

    private fun setTvSeriesRate(rate: Int) {
        TmdbUserUtil.setRatedTvSeriesRateValue(selectedTvSeries.id, rate)
        getTvSeriesRate()
    }

    private fun getTvDetailData() {
        tvSeriesDetailViewModel.getTvSeriesDetailList(selectedTvSeries.id)
        startLoading()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerFavorite()
        registerTvDetail()
        registerTvCast()
        registerPostTvSeriesRate()
        registerError()
    }

    private fun registerTvDetail() {
        tvSeriesDetailViewModel.tvDetailResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                tvDetail = it
                tvSeriesDetailViewModel.getTvSeriesCastList(selectedTvSeries.id)
            }
        })
    }
    private fun registerTvCast() {
        tvSeriesDetailViewModel.tvCastResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                tvCastList = it.cast
                initList(it.cast)
                initScreen()
            }
        })
    }

    private fun registerFavorite() {
        tvSeriesDetailViewModel.postFavoriteResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (isItemFavorite) {
                    IteratorUtil.deleteById(TmdbUser.userFavorites!!, selectedTvSeries.id)
                } else {
                    TmdbUser.userFavorites!!.add(FavoriteTvItem(selectedTvSeries))
                }
                isItemFavorite = !isItemFavorite
                notifyDataBinding(tvDetailBinding as ViewDataBinding)
            }
        })
    }

    private fun registerPostTvSeriesRate() {
        tvSeriesDetailViewModel.postTvRateResponse.observe(viewLifecycleOwner, Observer {
            if (!isTvSeriesRated) {
                isTvSeriesRated = true
                TmdbUser.userRatedTvSeries!!.add(selectedTvSeries as TvSeries)
            }
            setTvSeriesRate(rateValue!!)
        })
    }

    private fun registerError() {
        tvSeriesDetailViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    private fun setUpUI() {
        tvSeriesCastRecyclerview = requireView().findViewById(R.id.fragment_tv_detail_recycler_cast)
        val mLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        tvSeriesCastRecyclerview.layoutManager = mLayoutManager
    }

    private fun initComponents() {
        with(requireView()) {
            tvSeriesImage = findViewById(R.id.fragment_tv_series_detail_img)
        }
    }

    private fun initRateViewList() {
        with(requireView()) {
            tvSeriesRateList = listOf(
                findViewById(R.id.fragment_tv_series_detail_img_rate1),
                findViewById(R.id.fragment_tv_series_detail_img_rate2),
                findViewById(R.id.fragment_tv_series_detail_img_rate3),
                findViewById(R.id.fragment_tv_series_detail_img_rate4),
                findViewById(R.id.fragment_tv_series_detail_img_rate5)
            )
        }

        tvSeriesRateList.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                rateTvSeriesForValue((index + MoveeConstants.INT_ONE) * MoveeConstants.INT_TWO)
            }
        }
    }

    private fun rateTvSeriesForValue(_rateValue: Int?) {
        rateValue = _rateValue
        tvSeriesDetailViewModel.postTvSeriesRate(selectedTvSeries.id, rateValue!!)
    }

    private fun initScreen() {
        if (selectedTvSeries.genreIds != null) {
            genreString = GenreUtil.getGenre(selectedTvSeries.genreIds!!)
        }

        if (selectedTvSeries.posterPath != null) {
            GlideUtil.setImage(
                StringCompilerUtil.compileString(ImageSizeEnum.W_342.size, selectedTvSeries.posterPath!!),
                tvSeriesImage
            )
        }

        isItemFavorite = TmdbUserUtil.isItemFavorite(selectedTvSeries.id)

        val tvSeriesCastAdapter = GenericRecyclerAdapter(totalItemList, this)
        tvSeriesCastRecyclerview.adapter = tvSeriesCastAdapter

        stopLoading()
        setUpDataBinding()
    }
    //endregion

    //region Data
    private fun initList(castList: List<Cast>) {
        castList.forEach { cast ->
            (genericCastList as ArrayList).add(CastItem(cast))
        }

        totalItemList = ListJoinerUtil.concatenate(genericCastList)
    }
    //endregion

    //region Events
    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        val action =
            TvSeriesDetailFragmentDirections.actionTvSeriesDetailFragmentToTvCastDetailFragment(
                item as ICast
            )
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun rateButtonOnClick() {
        isRateClicked = !isRateClicked
        notifyDataBinding(tvDetailBinding as ViewDataBinding)
    }

    fun postTvSeriesFavorite() {
        tvSeriesDetailViewModel.postFavorite(
            PostFavoriteItemType.TV_TYPE.type,
            selectedTvSeries.id,
            !isItemFavorite
        )
    }

    fun shareUrl() {
        val tvSeriesEndPoint : String = getString(
            R.string.share_end_point,
            selectedTvSeries.id.toString()
        )

        IntentUtil.shareUrl(tvSeriesEndPoint)
    }
    //endregion

}