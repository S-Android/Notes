package com.example.notes.modules.splash.fragments.dagger

import androidx.lifecycle.ViewModelProvider
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.core.viewmodel.ViewModelProviderFactory
import com.example.notes.modules.splash.fragments.SplashFragment
import com.example.notes.modules.splash.fragments.repository.SplashRepository
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SplashFragmentModule(private val splashFragment: SplashFragment) {
    @FragmentScope
    @Named("FragmentLevelFactoryProvider")
    @Provides
    fun provideSplashFragmentViewModel(application: NotesApplication, repository: SplashRepository): SplashFragmentViewModel {
        return SplashFragmentViewModel(application, repository)
    }

    @FragmentScope
    @Named("FragmentLevelFactoryProvider")
    @Provides
    fun provideViewModelFactory(@Named("FragmentLevelFactoryProvider") viewModel: SplashFragmentViewModel):  ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}