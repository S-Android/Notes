package com.example.notes.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity: AppCompatActivity() {
    private var mViewModel: ViewModel?  = null
    var mDaggerComponent: BaseComponent? = null

    abstract fun setUpDaggerComponent(): BaseComponent?
    abstract fun setUpViewModel(): ViewModel?

    companion object {
        private lateinit var mInstance: BaseActivity
        fun getInstance(): BaseActivity {
            return mInstance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInstance = this

        mDaggerComponent = setUpDaggerComponent()
        mViewModel = setUpViewModel()
    }
}