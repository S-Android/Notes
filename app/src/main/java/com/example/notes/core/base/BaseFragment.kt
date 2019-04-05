package com.example.notes.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment: Fragment() {
    private var mViewModel: ViewModel?  = null
    private var mDaggerComponent: BaseComponent? = null

    abstract fun setUpDaggerComponent(): BaseComponent?
    abstract fun setUpViewModel(): ViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDaggerComponent = setUpDaggerComponent()
        mViewModel = setUpViewModel()
    }
}