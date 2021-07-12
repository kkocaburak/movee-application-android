package com.adesso.moveeapplication.ui.screens.dashboard.search.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import com.adesso.moveeapplication.ui.components.adapter.GenericRecyclerAdapter
import com.adesso.moveeapplication.ui.components.generic.ICastItem
import com.adesso.moveeapplication.ui.components.generic.SearchItem
import com.adesso.moveeapplication.ui.components.util.IOnItemClickListener
import com.adesso.moveeapplication.ui.screens.dashboard.search.base.SearchType
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainFragmentDirections
import com.adesso.moveeapplication.ui.screens.dashboard.search.viewmodel.SearchViewModel
import com.adesso.moveeapplication.ui.util.KeyboardUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


/**
 * Created by Burak Karakoca on 16.10.2020.
 */
class SearchFragment : BaseFragment(), IOnItemClickListener, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    lateinit var searchViewModel: SearchViewModel

    private lateinit var action : NavDirections
    private lateinit var searchEditText: EditText
    private lateinit var cancelIcon: ImageView
    private lateinit var resultLayout: LinearLayout
    private lateinit var cancelText: TextView
    private lateinit var searchRecyclerView: RecyclerView

    private var totalItemList: ArrayList<GenericItem> = arrayListOf()
    private var isInSearchDetail = false
    private var isSearchAvailable = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(SearchViewModel::class.java)

        registerLiveData()
        initComponents()
        initOnClicks()
        setUpUI()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            if (!isInSearchDetail) {
                initUI()
            } else {
                isInSearchDetail = false
            }
        }
    }

    private fun getData(search: String) {
        searchViewModel.getSearchResultList(search)
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerSearchResponse()
        registerSearchStatus()
    }

    private fun registerSearchResponse() {
        searchViewModel.searchResponse.observe(viewLifecycleOwner, Observer { multipleResponse ->

            totalItemList = arrayListOf()

            if (multipleResponse.results.size == MoveeConstants.INT_ZERO) {
                showResult(View.VISIBLE)
            } else {
                multipleResponse.results.forEach { result ->
                    if (isSearchAvailable) {
                        totalItemList.add(SearchItem(result))
                        showResult(View.INVISIBLE)
                    } else {
                        initScreen()
                    }

                }
            }

        })
    }

    private fun registerSearchStatus() {
        searchViewModel.searchStatus.observe(viewLifecycleOwner, Observer { searchStatus ->
            if (!searchStatus) {
                showResult(View.VISIBLE)
            }
        })
    }
    //endregion

    //region UI
    private fun initComponents() {
        cancelIcon = requireView().findViewById(R.id.fragment_search_ic_cancel)
        cancelText = requireView().findViewById(R.id.fragment_search_txt_cancel)
        resultLayout = requireView().findViewById(R.id.fragment_search_layout_result)

        resultLayout.visibility = View.INVISIBLE
    }

    private fun resetSearchText() {
        searchEditText.setText(MoveeApplication.appContext.getString(R.string.empty_string))
    }

    private fun setUpUI() {
        searchRecyclerView = requireView().findViewById(R.id.fragment_search_recyclerview)
        searchRecyclerView.layoutManager = LinearLayoutManager(MoveeApplication.appContext)

        searchEditText = requireView().findViewById(R.id.fragment_search_edit_text)

        searchEditText.addTextChangedListener(watcher)
    }

    private fun checkCancelIcon(text: CharSequence?) {
        if (text?.length != MoveeConstants.INT_ZERO) {
            cancelIcon.visibility = View.VISIBLE
        } else {
            cancelIcon.visibility = View.INVISIBLE
        }
    }

    private fun initUI() {
        searchEditText.requestFocus()
        KeyboardUtil.showKeyboard()
    }

    private fun initScreen() {
        val searchRecyclerAdapter = GenericRecyclerAdapter(totalItemList, this)
        searchRecyclerView.adapter = searchRecyclerAdapter
    }

    private fun showResult(visibility: Int) {
        initScreen()
        resultLayout.visibility = visibility
    }
    //endregion

    //region Events
    private val watcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            checkCancelIcon(s)
            checkSearchRequirement(s)
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
        }
    }

    private fun checkSearchRequirement(text: CharSequence?) {
        val searchFor : String
        val searchText = text.toString()

        if (searchText.length < MoveeConstants.INT_THREE) {
            isSearchAvailable = false
            totalItemList = arrayListOf()
            initScreen()
            return
        } else {
            isSearchAvailable = true
        }

        searchFor = searchText

        launch {
            delay(BaseUIConstants.SEARCH_DELAY)
            if (searchText != searchFor) {
                return@launch
            }

            if (text.toString().length >= MoveeConstants.INT_THREE) {
                getData(text.toString())
            }

        }
    }

    private fun initOnClicks() {
        cancelIcon.setOnClickListener {
            resetSearchText()
        }

        cancelText.setOnClickListener {
            resetSearchText()
            KeyboardUtil.hideKeyboard(requireView())
        }
    }

    override fun onItemClick(position: Int?, item: Any, viewType: Int) {
        setIsInMainFragment(false)

        action = when {
            (item as SearchItem).item.mediaType == SearchType.MOVIE.type -> {
                MainFragmentDirections.actionMainFragmentToMovieInfo(
                    item.item
                )
            }
            item.item.mediaType == SearchType.TV_SERIES.type -> {
                MainFragmentDirections.actionMainFragmentToTvSeriesDetailFragment(
                    item.item
                )
            }
            else -> {
                MainFragmentDirections.actionMainFragmentToCastDetailFragment(
                    (item as ICastItem).item
                )
            }
        }

        isInSearchDetail = true
        Navigation.findNavController(requireView()).navigate(action)
    }
    //endregion

}

