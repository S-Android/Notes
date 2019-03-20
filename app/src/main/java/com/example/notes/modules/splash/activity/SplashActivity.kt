package com.example.notes.modules.splash.activity

import android.content.Context
import android.os.Bundle
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.dagger.qualifiers.ActivityContext
import com.example.notes.core.dagger.qualifiers.ApplicationContext
import com.example.notes.modules.splash.activity.viewmodel.SplashActivityViewModel
import retrofit2.http.Field
import javax.inject.Inject

class SplashActivity: BaseActivity<SplashActivityViewModel>() {
    @Inject
    @field:ApplicationContext
    lateinit var  appContext1: Context
//
    @Inject
    @field:ApplicationContext
    lateinit var appContext2: Context

    @Inject
    @field:ActivityContext
    lateinit var activityContext1: Context

    @Inject
    @field:ActivityContext
    lateinit var activityContext2: Context

    override fun getViewModel(): SplashActivityViewModel {
        return SplashActivityViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        print("hello")
    }

}