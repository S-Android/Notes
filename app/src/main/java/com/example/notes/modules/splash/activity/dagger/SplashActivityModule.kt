package com.example.notes.modules.splash.activity.dagger

import android.content.Context
import com.example.notes.core.dagger.qualifiers.ActivityContext
import com.example.notes.core.dagger.scopes.ActivityScope
import com.example.notes.modules.splash.activity.SplashActivity
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {
//    @ActivityScope
    @ActivityContext
    @Provides
    fun provideActivityContext(splashActivity: SplashActivity): Context {
        return splashActivity
    }
}