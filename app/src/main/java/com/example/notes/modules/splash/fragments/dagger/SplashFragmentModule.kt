package com.example.notes.modules.splash.fragments.dagger

import androidx.lifecycle.ViewModelProvider
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.dagger.qualifiers.FragmentLevelFactoryProvider
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.core.viewmodel.ViewModelProviderFactory
import com.example.notes.modules.splash.activity.dagger.SplashActivityComponent
import com.example.notes.modules.splash.fragments.SplashFragment
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashFragmentModule(private val splashFragment: SplashFragment) {
    @FragmentScope
    @FragmentLevelFactoryProvider
    @Provides
    fun provideSplashFragmentViewModel(): SplashFragmentViewModel {
        return SplashFragmentViewModel()
    }

    @FragmentScope
    @FragmentLevelFactoryProvider
    @Provides
    fun provideViewModelFactory(viewModel: SplashFragmentViewModel):  ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}