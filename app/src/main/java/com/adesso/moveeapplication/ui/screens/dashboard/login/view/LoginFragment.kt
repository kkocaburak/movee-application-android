package com.adesso.moveeapplication.ui.screens.dashboard.login.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbSessionSingleton
import com.adesso.moveeapplication.data.model.tmdb.LoginInfo
import com.adesso.moveeapplication.databinding.FragmentLoginBinding
import com.adesso.moveeapplication.databinding.FragmentMovieDetailBinding
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.screens.dashboard.login.viewmodel.LoginViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.IntentUtil

class LoginFragment : BaseFragment() {

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var user: LoginInfo

    private var loginFragmentBinding: FragmentLoginBinding? = null
    var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stopLoading()
        hideBottomTabBar()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginFragmentBinding = FragmentLoginBinding.bind(view)
        loginFragmentBinding?.loginFragment = this

        loginViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(LoginViewModel::class.java)

        registerLiveData()
        initComponents()
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerToken()
        registerUserToken()
        registerUserSession()
        registerGuestSession()
        registerError()
    }

    private fun registerToken() {
        loginViewModel.tokenResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                user = LoginInfo(
                    usernameText.text.toString(),
                    passwordText.text.toString(),
                    it.requestToken
                )
                loginViewModel.getUserToken(user)
            } else {
                stopLoginLoading()
                AlertDialogUtil.showAlert(it.statusMessage, context)
            }
        })
    }

    private fun registerUserToken() {
        loginViewModel.userTokenResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                loginViewModel.getUserSession(it.requestToken)
            } else {
                stopLoginLoading()
                AlertDialogUtil.showAlert(it.statusMessage, context)
            }
        })
    }

    private fun registerUserSession() {
        loginViewModel.userSessionResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                TmdbSessionSingleton.sessionId = it.sessionId
                TmdbSessionSingleton.isGuest = false
                goToMainScreen()
            } else {
                stopLoginLoading()
                AlertDialogUtil.showAlert(it.statusMessage, context)
            }
        })
    }

    private fun registerGuestSession() {
        loginViewModel.guestSessionResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                TmdbSessionSingleton.sessionId = it.guestSessionId
                TmdbSessionSingleton.isGuest = true
                goToMainScreen()
            } else {
                stopLoginLoading()
                AlertDialogUtil.showAlert(it.statusMessage, context)
            }

        })
    }

    private fun registerError() {
        loginViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            stopLoginLoading()
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    private fun initComponents() {
        with(requireView()) {
            usernameText = findViewById(R.id.fragment_login_edit_txt_username)
            passwordText = findViewById(R.id.fragment_login_edit_text_password)
        }
    }

    private fun startLoginLoading() {
        isLoading = true
        loginFragmentBinding?.invalidateAll()
    }

    private fun stopLoginLoading() {
        isLoading = false
        loginFragmentBinding?.invalidateAll()
    }
    //endregion UI

    //region Events
    fun loginOnClick() {
        startLoginLoading()
        loginViewModel.getToken()
    }

    fun loginAsGuestOnClick() {
        startLoginLoading()
        loginViewModel.getGuestSession()
    }

    fun forgotPasswordOnClick() {
        IntentUtil.toWebsite(getString(R.string.forgot_password_url))
    }

    fun registerOnClick() {
        IntentUtil.toWebsite(getString(R.string.sign_up_url))
    }

    private fun goToMainScreen() {
        val action =
            LoginFragmentDirections.actionUserFragmentToMainFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }
    //endregion


}