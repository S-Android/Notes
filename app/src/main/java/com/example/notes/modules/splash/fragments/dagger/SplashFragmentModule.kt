package com.example.notes.modules.splash.fragments.dagger

import androidx.lifecycle.ViewModelProvider
import com.example.notes.core.viewmodel.ViewModelProviderFactory
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashFragmentModule {
    @Provides
    fun provideViewModelFactory(viewModel: SplashFragmentViewModel):  ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}