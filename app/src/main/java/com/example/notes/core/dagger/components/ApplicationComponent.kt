package com.example.notes.core.dagger.components

import com.example.notes.core.dagger.modules.ApplicationModule
import com.example.notes.core.dagger.scopes.ApplicationScope
import com.example.notes.modules.home.activity.dagger.HomeComponent
import com.example.notes.modules.home.activity.dagger.HomeModule
import com.example.notes.modules.splash.activity.dagger.SplashActivityComponent
import com.example.notes.modules.splash.activity.dagger.SplashActivityModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun addSubComponent(splashActivityModule: SplashActivityModule): SplashActivityComponent
    fun addSubComponent(homeModule: HomeModule): HomeComponent
}