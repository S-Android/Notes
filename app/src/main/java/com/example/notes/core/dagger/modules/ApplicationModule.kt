package com.example.notes.core.dagger.modules

import android.content.Context
import androidx.work.WorkManager
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.qualifiers.ApplicationContext
import com.example.notes.core.dagger.scopes.ApplicationScope
import com.example.notes.core.database.NotesDatabase
import com.example.notes.core.firebase.NotesFirebase
import com.example.notes.core.workmanager.NotesWorkManager
import com.google.firebase.database.FirebaseDatabase
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

    @ApplicationScope
    @Provides
    fun provideApplication(): NotesApplication {
        return application
    }

    @ApplicationScope
    @Provides
    fun provideNotesDatabase(): NotesDatabase {
        return NotesDatabase.getDatabase(application)
    }

    @ApplicationScope
    @Provides
    fun provideWorkManager(): NotesWorkManager {
        return NotesWorkManager.getNotesWorker()!!
    }

    @ApplicationScope
    @Provides
    fun provideFirebase(): NotesFirebase {
        return NotesFirebase.getFirebaseInstance()!!
    }
}