package com.example.notes.core.dagger.modules

import android.content.Context
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @ApplicationContext
    @Provides
    fun provideApplicationContext(application: NotesApplication): Context {
        return application
    }
}