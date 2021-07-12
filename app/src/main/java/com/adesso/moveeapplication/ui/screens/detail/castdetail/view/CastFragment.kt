package com.adesso.moveeapplication.ui.screens.detail.castdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.CastDetailResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.CastMoviesResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.util.ListJoinerUtil
import com.adesso.moveeapplication.data.model.tmdb.ICast
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.ui.base.ImageSizeEnum
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.generic.CastMoviesItem
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.detail.castdetail.base.CastBaseFragment
import com.adesso.moveeapplication.ui.screens.detail.castdetail.viewmodel.CastViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.DateFormatterUtil
import com.adesso.moveeapplication.ui.util.GlideUtil
import com.adesso.moveeapplication.ui.util.StringCompilerUtil
import kotlin.collections.ArrayList

class CastFragment : CastBaseFragment(), IOnItemClickListener {

    private lateinit var cast : ICast
    private lateinit var castDetail : CastDetailResponse
    private lateinit var castMovie : CastMoviesResponse
    private lateinit var castMoviesRecycler: RecyclerView
    private lateinit var totalItemList: List<GenericItem>
    private var genericCastList: List<CastMoviesItem> = ArrayList()

    private lateinit var castImage : ImageView
    private lateinit var castNameText : TextView
    private lateinit var castBioText : TextView
    private lateinit var castBornText : TextView
    private lateinit var castBornStringText : TextView
    private lateinit var seeMoreText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        castViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(CastViewModel::class.java)

        arguments?.let {
            cast = CastFragmentArgs.fromBundle(
                it
            ).castDetail
        }

        return inflater.inflate(R.layout.fragment_cast_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLiveData()
        getCastData()
    }

    private fun getCastData() {
        castViewModel.getTvCastDetail(cast.id)
        castViewModel.getTvCastMovies(cast.id)
        startLoading()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerCastDetail()
        registerCastMovies()
        registerError()
    }
    private fun registerCastDetail() {
        castViewModel.castDetailResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                castDetail = it
                initUI()
            }
        })
    }
    private fun registerCastMovies() {
        castViewModel.castMoviesResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                castMovie = it
                totalItemList = setGenericList(genericCastList, castMovie.castMovies)
                setUpUI()
                initRecycler()
            }
        })
    }
    private fun registerError() {
        castViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    private fun initUI() {
        castImage = requireView().findViewById(R.id.fragment_cast_detail_img)
        castNameText = requireView().findViewById(R.id.fragment_cast_detail_txt_name)
        castBioText = requireView().findViewById(R.id.fragment_cast_detail_txt_bio)
        castBornText = requireView().findViewById(R.id.fragment_cast_detail_txt_born)
        castBornStringText = requireView().findViewById(R.id.fragment_cast_detail_txt_string_born)
        seeMoreText = requireView().findViewById(R.id.fragment_cast_detail_txt_see_more)
        checkNullData()
    }

    private fun checkNullData() {
        if (cast.profilePath == null) {
            castImage.setImageResource(R.mipmap.ic_app)
        }
        if (castDetail.biography.isNullOrBlank()) {
            castBioText.visibility = View.GONE
            seeMoreText.visibility = View.GONE
        }
        if (castDetail.birthday == null) {
            castBornStringText.visibility = View.GONE
        }
        initScreen()
    }

    private fun setUpUI() {
        castMoviesRecycler = requireView().findViewById(R.id.fragment_cast_detail_recycler_movies)
        val mLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        castMoviesRecycler.layoutManager = mLayoutManager
    }

    private fun initScreen() {
        cast.profilePath?.let {
            GlideUtil.setImage(
                StringCompilerUtil.compileString(ImageSizeEnum.W_342.size, cast.profilePath!!),
                castImage
            )
        }
        cast.name?.let {
            castNameText.text = cast.name
        }
        castDetail.biography?.let {
            castBioText.maxLines = BaseUIConstants.MIN_TEXT_LINE
            castBioText.text = castDetail.biography
        }
        castDetail.birthday?.let { birthday ->
            castDetail.placeOfBirth?.let { placeOfBirth ->
                castBornText.text = DateFormatterUtil.formatDate(
                    birthday,
                    placeOfBirth
                )
            }
        }

        seeMoreTextOnClick()

        stopLoading()
    }

    private fun initRecycler() {
        val castMovieAdapter =
            GenericRecyclerAdapter(
                totalItemList,
                this
            )
        castMoviesRecycler.adapter = castMovieAdapter
    }
    //endregion

    //region Data
    private fun setGenericList(genericItemList: List<GenericItem>, castList: List<Movie>) : List<GenericItem> {
        castList.forEach { cast ->
            (genericItemList as ArrayList).add(CastMoviesItem(cast))
        }
        return ListJoinerUtil.concatenate(genericItemList)
    }
    //endregion

    //region Events
    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        val action =
            CastFragmentDirections.actionTvCastDetailFragmentToMovieInfo(
                item as Movie
            )
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun seeMoreTextOnClick() {
        seeMoreText.setOnClickListener {
            if (castBioText.maxLines == BaseUIConstants.MAX_TEXT_LINE) {
                castBioText.maxLines = BaseUIConstants.MIN_TEXT_LINE
                seeMoreText.text = getString(R.string.full_bio_string)
            } else {
                castBioText.maxLines = BaseUIConstants.MAX_TEXT_LINE
                seeMoreText.text = getString(R.string.less_bio_string)
            }
        }
    }
    //endregion

}