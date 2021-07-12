package com.adesso.moveeapplication.ui.screens.settings.credits.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseFragment

class CreditsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideBottomTabBar()
        return inflater.inflate(R.layout.fragment_credits, container, false)
    }

}