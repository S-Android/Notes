package com.example.notes.modules.splash.fragments.dagger

import com.example.notes.modules.splash.fragments.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashFragmentProvider {
    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    abstract fun contributeSplashFragment(): SplashFragment
}