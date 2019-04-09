package com.example.notes.core.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<viewModel: ViewModel, component: BaseComponent>: Fragment() {
    protected var mViewModel: viewModel?  = null
    private var mDaggerComponent: component? = null

    abstract fun setUpDaggerComponent(): component?
    abstract fun setUpViewModel(): viewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDaggerComponent = setUpDaggerComponent()
        mViewModel = setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}