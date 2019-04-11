package com.example.notes.core.base

import androidx.lifecycle.AndroidViewModel
import com.example.notes.NotesApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel(application: NotesApplication): AndroidViewModel(application), CoroutineScope {
    override val coroutineContext = Job() + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()

        coroutineContext.cancel()
    }
}