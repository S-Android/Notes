package com.example.notes.modules.splash.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.base.BaseComponent
import com.example.notes.core.base.BaseFragment
import com.example.notes.core.dagger.qualifiers.FragmentLevelFactoryProvider
import com.example.notes.modules.splash.activity.dagger.SplashActivityComponent
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentComponent
import com.example.notes.modules.splash.fragments.dagger.SplashFragmentModule
import com.example.notes.modules.splash.fragments.viewmodel.SplashFragmentViewModel
import javax.inject.Inject
import javax.inject.Named

class SplashFragment: BaseFragment<SplashFragmentViewModel, SplashFragmentComponent>() {

    @Inject @field:Named("FragmentLevelFactoryProvider") lateinit var factory: ViewModelProvider.Factory

    var mSplashFragmentComponent: SplashFragmentComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel?.splashTimerLiveData?.observe(this, Observer {
            Toast.makeText(this@SplashFragment.activity, "hello world", Toast.LENGTH_SHORT).show()
        })

        mViewModel?.repository?.workManager?.getWorkInfoByIdLiveData(mViewModel?.oneTimeRequest!!.id)?.observe(this, Observer {
            if (it.state.isFinished) {
                val message = it.outputData.getString("output_message")
                Toast.makeText(this@SplashFragment.activity, "work finished $message", Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel?.init()
    }

    override fun setUpViewModel(): SplashFragmentViewModel? {
        return ViewModelProviders.of(this, factory).get(SplashFragmentViewModel::class.java)
    }

    override fun setUpDaggerComponent(): SplashFragmentComponent? {
        if (mSplashFragmentComponent == null) {
            val component = BaseActivity.getInstance().mDaggerComponent
            if (component is SplashActivityComponent) {
                mSplashFragmentComponent = component.splashFragmentComponent(SplashFragmentModule(this))
                mSplashFragmentComponent?.inject(this)
            }
        }
        return mSplashFragmentComponent
    }
}