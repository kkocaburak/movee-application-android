package com.adesso.moveeapplication.ui.screens.settings.setting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.databinding.FragmentSettingsBinding
import com.adesso.moveeapplication.databinding.FragmentUserProfileBinding
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainFragmentDirections

class SettingsFragment : BaseFragment() {

    private var settingsFragmentBinding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideBottomTabBar()
        setIsInMainFragment(false)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsFragmentBinding = FragmentSettingsBinding.bind(view)
        settingsFragmentBinding?.settingsFragment = this
    }

    fun goToCreditsScreen() {
        val action =
            SettingsFragmentDirections.actionSettingsFragmentToCreditsFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun goToLicencesScreen() {
        val action =
            SettingsFragmentDirections.actionSettingsFragmentToLicencesFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

}