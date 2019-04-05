package com.example.notes.modules.splash.activity.dagger

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.qualifiers.ActivityContext
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.core.dagger.scopes.ActivityScope
import com.example.notes.core.viewmodel.ViewModelProviderFactory
import com.example.notes.modules.splash.activity.SplashActivity
import com.example.notes.modules.splash.activity.viewmodel.SplashActivityViewModel
import com.example.notes.modules.splash.fragments.SplashFragment
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule(private val splashActivity: SplashActivity) {
    @ActivityScope
    @ActivityContext
    @Provides
    fun provideActivityContext(): Context {
        return splashActivity
    }

    @Provides
    fun provideSplashFragment(): Fragment {
        return SplashFragment()
    }

    @ActivityScope
    @ActivityLevelFactoryProvider
    @Provides
    fun provideSplashActivityViewModel(): SplashActivityViewModel {
        return SplashActivityViewModel()
    }

    @ActivityScope
    @ActivityLevelFactoryProvider
    @Provides
    fun provideViewModelFactory(viewModel: SplashActivityViewModel):  ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}