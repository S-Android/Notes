package com.example.notes.modules.splash.fragments.repository

import androidx.work.Constraints
import androidx.work.Data
import com.example.notes.core.database.NotesDatabase
import kotlinx.coroutines.*
import javax.inject.Inject
import androidx.work.OneTimeWorkRequest
import com.example.notes.core.workmanager.DummyWorker
import com.example.notes.core.workmanager.NotesWorkManager


class SplashRepository @Inject constructor(private val notesDatabase: NotesDatabase, val workManager: NotesWorkManager) {
    suspend fun waitingOnSplashScreen() = coroutineScope {
        async {
            delay(3000)
        }
    }

    fun getOneTimeWorkRequest(): OneTimeWorkRequest {
        val data = Data.Builder()
            .putString("title", "Demo Title")
            .putString("message", "Demo Message")
            .build()
        val constraints = Constraints.Builder().setRequiresCharging(true).build()

        return OneTimeWorkRequest.Builder(DummyWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
    }
}