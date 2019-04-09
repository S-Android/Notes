package com.example.notes.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory(private val viewModel: Any?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(viewModel!!::class.java)) {
            return viewModel as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}