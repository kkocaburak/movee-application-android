package com.adesso.moveeapplication.ui.screens.settings.licences.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.databinding.FragmentLicensesBinding
import com.adesso.moveeapplication.databinding.FragmentSettingsBinding
import com.adesso.moveeapplication.ui.base.BaseFragment

class LicensesFragment : BaseFragment() {

    private lateinit var webView : WebView

    lateinit var glideUrl : String
    lateinit var retrofitUrl : String
    lateinit var rxJavaUrl : String
    lateinit var rxBindingUrl : String

    private var licenseFragmentBinding: FragmentLicensesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideBottomTabBar()
        return inflater.inflate(R.layout.fragment_licenses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        licenseFragmentBinding = FragmentLicensesBinding.bind(view)
        licenseFragmentBinding?.licenseFragment = this

        initScreen()

    }

    private fun initScreen() {
        webView = requireView().findViewById(R.id.fragment_licenses_web_view)

        glideUrl = getString(R.string.glide_url)
        retrofitUrl = getString(R.string.retrofit_url)
        rxJavaUrl = getString(R.string.rx_java_url)
        rxBindingUrl = getString(R.string.rx_binding_url)
    }

    fun onLicenseClick(url: String) {
        webView.loadUrl(url)
    }

}