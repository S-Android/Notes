package com.example.notes.core.dagger.modules

import android.content.Context
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.qualifiers.ApplicationContext
import com.example.notes.core.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: NotesApplication) {
    @ApplicationContext
    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context {
        return application
    }
}