package com.example.notes.modules.splash.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.base.BaseComponent
import com.example.notes.core.base.BaseFragment
import com.example.notes.core.dagger.qualifiers.FragmentLevelFactoryProvider
import com.example.notes.modules.splash.activity.dagger.SplashActivityComponent
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentComponent
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentModule
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import javax.inject.Inject

class SplashFragment: BaseFragment() {

    @Inject @field:FragmentLevelFactoryProvider lateinit var factory: ViewModelProvider.Factory

    var mSplashFragmentComponent: SplashFragmentComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun setUpViewModel(): SplashFragmentViewModel? {
        return ViewModelProviders.of(this, factory).get(SplashFragmentViewModel::class.java)
    }

    override fun setUpDaggerComponent(): BaseComponent? {
        if (mSplashFragmentComponent == null) {
            val component = BaseActivity.getInstance().mDaggerComponent
            if (component is SplashActivityComponent) {
                mSplashFragmentComponent = component.splashFragmentComponent(SplashFragmentModule(this))
                mSplashFragmentComponent?.inject(this)
            }
        }
        return mSplashFragmentComponent
    }
}