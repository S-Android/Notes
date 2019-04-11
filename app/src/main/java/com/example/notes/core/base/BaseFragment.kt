package com.example.notes.core.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<viewModel: ViewModel, component: BaseComponent>: Fragment() {
    var viewModel: viewModel?  = null
    var daggerComponent: component? = null

    abstract fun setUpDaggerComponent()
    abstract fun setUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDaggerComponent()
        setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}