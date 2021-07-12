package com.adesso.moveeapplication.ui.screens.detail.moviedetail.view

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
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieCastResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.util.ListJoinerUtil
import com.adesso.moveeapplication.data.model.tmdb.Cast
import com.adesso.moveeapplication.data.model.tmdb.ICast
import com.adesso.moveeapplication.data.model.tmdb.IMovie
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.databinding.FragmentMovieDetailBinding
import com.adesso.moveeapplication.ui.base.ImageSizeEnum
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.CastItem
import com.adesso.moveeapplication.ui.components.generic.FavoriteMovieItem
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.detail.base.PostFavoriteItemType
import com.adesso.moveeapplication.ui.screens.detail.moviedetail.base.MovieDetailBaseFragment
import com.adesso.moveeapplication.ui.screens.detail.moviedetail.viewmodel.MovieDetailViewModel
import com.adesso.moveeapplication.ui.util.*

class MovieDetailFragment : MovieDetailBaseFragment(), IOnItemClickListener {

    lateinit var selectedMovie: IMovie
    private lateinit var movieCast: MovieCastResponse
    private var genericCastList: List<CastItem> = ArrayList()
    private lateinit var totalItemList: List<GenericItem>

    private lateinit var movieImage: ImageView
    private lateinit var movieCastRecyclerview: RecyclerView
    private lateinit var movieRateList: List<ImageView>

    private var movieDetailBinding: FragmentMovieDetailBinding? = null
    private var isMovieRated = false
    private var rateValue : Int? = null

    var genreString : String? = null
    var directorString : String? = null
    var writersString : String? = null
    var starsString : String? = null
    var movieRateString : String? = null

    var isRateClicked = false
    var isItemFavorite = false
    var isGuest = TmdbSessionSingleton.isGuest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        arguments?.let {
            selectedMovie = MovieDetailFragmentArgs.fromBundle(it).movieDetail
        }
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailBinding = FragmentMovieDetailBinding.bind(view)
        movieDetailBinding?.lifecycleOwner = this

        setUpViewModel()
        registerLiveData()
        setUpUI()
        initComponents()
        getMovieDetailData()
        checkForGuest()
    }

    private fun setUpViewModel() {
        movieDetailViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(MovieDetailViewModel::class.java)
    }

    private fun setUpDataBinding() {
        movieDetailBinding?.movieDetailFragment = this
        movieDetailBinding?.movieDetailViewModel = movieDetailViewModel
    }

    private fun checkForGuest() {
        if(!isGuest) {
            isMovieRated = TmdbUserUtil.isMovieRated(selectedMovie.id)
            getMovieRate()
        }
    }

    private fun getMovieRate() {
        movieRateString = getString(
            R.string.rate_string,
            TmdbUserUtil.getRatedMovieRateValue(selectedMovie.id).toString()
        )
        updateRateStars(movieRateList, TmdbUserUtil.getRatedMovieRateValue(selectedMovie.id))
        notifyDataBinding(movieDetailBinding as ViewDataBinding)
    }

    private fun setMovieRate(rate: Int) {
        TmdbUserUtil.setRatedMovieRateValue(selectedMovie.id, rate)
        getMovieRate()
    }

    private fun getMovieDetailData() {
        movieDetailViewModel.getMovieCastList(selectedMovie.id)
        movieDetailViewModel.getMovieDetailList(selectedMovie.id)
        startLoading()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerMovieCast()
        registerPostFavorite()
        registerPostMovieRate()
    }

    private fun registerMovieCast() {
        movieDetailViewModel.movieCastResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                movieCast = it
                initList(it.cast)
                initScreen()
            }
        })
    }

    private fun registerPostFavorite() {
        movieDetailViewModel.postFavoriteResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (isItemFavorite) {
                    IteratorUtil.deleteById(TmdbUser.userFavorites!!, selectedMovie.id)
                } else {
                    TmdbUser.userFavorites!!.add(FavoriteMovieItem(selectedMovie))
                }
                isItemFavorite = !isItemFavorite
                notifyDataBinding(movieDetailBinding as ViewDataBinding)
            }
        })
    }

    private fun registerPostMovieRate() {
        movieDetailViewModel.postMovieRateResponse.observe(viewLifecycleOwner, Observer {
            if (!isMovieRated) {
                isMovieRated = true
                TmdbUser.userRatedMovies!!.add(selectedMovie as Movie)
            }
            setMovieRate(rateValue!!)
        })
    }
    //endregion

    //region UI
    private fun setUpUI() {
        movieCastRecyclerview = requireView().findViewById(R.id.fragment_movie_detail_recycler_cast)
        val mLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        movieCastRecyclerview.layoutManager = mLayoutManager
    }

    private fun initComponents() {
        movieImage = requireView().findViewById(R.id.fragment_movie_detail_img)
        initRateViewList()
    }

    private fun initRateViewList() {
        with(requireView()) {
            movieRateList = listOf(
                findViewById(R.id.fragment_movie_detail_img_rate1),
                findViewById(R.id.fragment_movie_detail_img_rate2),
                findViewById(R.id.fragment_movie_detail_img_rate3),
                findViewById(R.id.fragment_movie_detail_img_rate4),
                findViewById(R.id.fragment_movie_detail_img_rate5)
            )
        }

        movieRateList.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                rateMovieForValue((index + MoveeConstants.INT_ONE) * MoveeConstants.INT_TWO)
            }
        }
    }

    private fun initScreen() {
        if (selectedMovie.genreIds != null) {
            genreString = GenreUtil.getGenre(selectedMovie.genreIds!!)
        }

        if (selectedMovie.posterPath != null) {
          GlideUtil.setImage(
              StringCompilerUtil.compileString(ImageSizeEnum.W_342.size, selectedMovie.posterPath!!),
              movieImage
          )
        }

        isItemFavorite = TmdbUserUtil.isItemFavorite(selectedMovie.id)
        directorString = getMovieDirector()
        writersString = getMovieWriters()
        starsString = getMovieStars()

        val movieCastAdapter = GenericRecyclerAdapter(totalItemList, this)
        movieCastRecyclerview.adapter = movieCastAdapter

        stopLoading()
        setUpDataBinding()
    }

    private fun getMovieWriters() : String? {
        val stringBuilder = StringBuilder()

        movieCast.crew.forEach { crewMember ->
            if (crewMember.job == getString(R.string.writer_string)) {
                if (stringBuilder.isNotEmpty()) {
                    stringBuilder.append(getString(R.string.movie_recyclerview_genre_separator))
                }
                stringBuilder.append(crewMember.name)
            }
        }

        val writers = stringBuilder.toString()

        if (writers.isBlank()) {
            return null
        }

        return writers
    }

    private fun getMovieDirector() : String? {
        movieCast.crew.forEach { crewMember ->
            if (crewMember.job == getString(R.string.director_string)) {
                return crewMember.name
            }
        }
        return null
    }

    private fun getMovieStars() : String? {
        if (movieCast.cast.size > MoveeConstants.INT_ONE) {
            return movieCast.cast[MoveeConstants.INT_ZERO].name +
                    getString(R.string.movie_recyclerview_genre_separator) +
                    movieCast.cast[MoveeConstants.INT_ONE].name
        }
        return movieCast.cast[MoveeConstants.INT_ZERO].name!!
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
            MovieDetailFragmentDirections.actionMovieInfoToTvCastDetailFragment(
                item as ICast
            )
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun shareUrl() {
        val movieEndPoint : String = getString(
            R.string.share_end_point,
            selectedMovie.id.toString()
        )
        IntentUtil.shareUrl(movieEndPoint)
    }

    fun rateButtonOnClick() {
        isRateClicked = !isRateClicked
        notifyDataBinding(movieDetailBinding as ViewDataBinding)
    }

    fun postMovieFavorite() {
        movieDetailViewModel.postFavorite(
            PostFavoriteItemType.MOVIE_TYPE.type,
            selectedMovie.id,
            !isItemFavorite
        )
    }

    private fun rateMovieForValue(_rateValue: Int?) {
        rateValue = _rateValue
        movieDetailViewModel.postMovieRate(selectedMovie.id, rateValue!!)
    }
    //endregion

}