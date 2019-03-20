package com.example.notes.core.dagger.builders

import com.example.notes.modules.splash.activity.SplashActivity
import com.example.notes.modules.splash.activity.dagger.SplashActivityModule
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class, SplashFragmentProvider::class])
    abstract fun contributeSplashActivity(): SplashActivity
}