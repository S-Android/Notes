package com.example.notes.modules.splash.fragments.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.qualifiers.FragmentLevelFactoryProvider
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.modules.splash.fragments.SplashFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SplashFragmentModule::class])
interface SplashFragmentComponent: BaseComponent {
    fun inject(splashFragment: SplashFragment)
}