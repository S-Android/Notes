package com.example.notes.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<viewModel: ViewModel, daggerComponent: BaseComponent>: AppCompatActivity() {
    var viewModel: viewModel?  = null
    var daggerComponent: daggerComponent? = null

    abstract fun setUpDaggerComponent()
    abstract fun setUpViewModel()

    companion object {
        private lateinit var mInstance: BaseActivity<*, *>
        fun getInstance(): BaseActivity<*, *> {
            return mInstance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInstance = this

        setUpDaggerComponent()
        setUpViewModel()
    }
}