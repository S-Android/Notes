package com.example.notes

import android.app.Application
import com.example.notes.core.dagger.components.ApplicationComponent
import com.example.notes.core.dagger.components.DaggerApplicationComponent
import com.example.notes.core.dagger.modules.ApplicationModule

class NotesApplication: Application() {
    lateinit var mAppComponent: ApplicationComponent

    companion object {
        private lateinit var mInstance: NotesApplication
        fun getInstance(): NotesApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        mAppComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getAppComponent(): ApplicationComponent {
        return mAppComponent
    }
}