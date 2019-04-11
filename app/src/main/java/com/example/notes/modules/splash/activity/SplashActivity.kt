package com.example.notes.modules.splash.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.NotesApplication
import com.example.notes.R
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.dagger.qualifiers.ActivityContext
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.core.dagger.qualifiers.ApplicationContext
import com.example.notes.modules.splash.activity.dagger.SplashActivityComponent
import com.example.notes.modules.splash.activity.dagger.SplashActivityModule
import com.example.notes.modules.splash.activity.viewmodel.SplashActivityViewModel

import javax.inject.Inject

class SplashActivity: BaseActivity<SplashActivityViewModel, SplashActivityComponent>() {
    @Inject
    @field:ApplicationContext
    lateinit var  appContext1: Context

    @Inject
    @field:ApplicationContext
    lateinit var appContext2: Context

    @Inject
    @field:ActivityContext
    lateinit var activityContext1: Context

    @Inject
    @field:ActivityContext
    lateinit var activityContext2: Context

    @Inject lateinit var mSplashFragment: Fragment
    @Inject @field:ActivityLevelFactoryProvider lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, mSplashFragment)
            .commit()
    }

    override fun setUpDaggerComponent() {
        if (daggerComponent == null) {
            daggerComponent = NotesApplication.getInstance().getAppComponent()
                .addSubComponent(SplashActivityModule(this))
            daggerComponent?.inject(this)
        }
    }

    override fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SplashActivityViewModel::class.java)
    }
}