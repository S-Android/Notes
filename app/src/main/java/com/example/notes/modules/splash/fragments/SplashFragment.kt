package com.example.notes.modules.splash.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.core.base.BaseFragment
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import javax.inject.Inject

class SplashFragment: BaseFragment<SplashFragmentViewModel>() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun getViewModel(): SplashFragmentViewModel {
        return ViewModelProviders.of(this, factory).get(SplashFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("hello")
    }
}