package com.example.notes.modules.splash.activity.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.core.dagger.scopes.ActivityScope
import com.example.notes.modules.splash.activity.SplashActivity
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentComponent
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SplashActivityModule::class])
interface SplashActivityComponent: BaseComponent {
    fun inject(splashActivity: SplashActivity)
    fun splashFragmentComponent(splashFragmentModule: SplashFragmentModule): SplashFragmentComponent
}