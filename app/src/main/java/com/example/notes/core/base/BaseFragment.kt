package com.example.notes.core.base

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T: ViewModel>: DaggerFragment() {
    private var viewModel: T? = null

    abstract fun getViewModel(): T

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = getViewModel()
    }
}