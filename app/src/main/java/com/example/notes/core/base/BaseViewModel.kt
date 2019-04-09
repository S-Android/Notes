package com.example.notes.core.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel(context: Context): AndroidViewModel(context as Application), CoroutineScope {
    override val coroutineContext = Job() + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()

        coroutineContext.cancel()
    }
}