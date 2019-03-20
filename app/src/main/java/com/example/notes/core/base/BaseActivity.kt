package com.example.notes.core.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T: ViewModel>: DaggerAppCompatActivity() {
    private var viewModel: T? = null

    abstract fun getViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = if (viewModel == null) getViewModel() else viewModel
    }
}