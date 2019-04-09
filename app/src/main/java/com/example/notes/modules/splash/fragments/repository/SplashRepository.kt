package com.example.notes.modules.splash.fragments.repository

import com.example.notes.core.database.NotesDatabase
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashRepository @Inject constructor(private val notesDatabase: NotesDatabase) {
    suspend fun waitingOnSplashScreen() = coroutineScope {
        async {
            delay(3000)
        }
    }
}