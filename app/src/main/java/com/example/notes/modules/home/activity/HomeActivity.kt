package com.example.notes.modules.home.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.NotesApplication
import com.example.notes.R
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.modules.home.activity.dagger.HomeComponent
import com.example.notes.modules.home.activity.dagger.HomeModule
import com.example.notes.modules.home.activity.viewmodel.HomeViewModel
import com.example.notes.modules.home.fragments.folderlist.FolderListFragment
import javax.inject.Inject

class HomeActivity: BaseActivity<HomeViewModel, HomeComponent>() {
    @Inject lateinit var folderListFragment: FolderListFragment
    @Inject @field:ActivityLevelFactoryProvider lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, folderListFragment)
            .commit()
    }

    override fun setUpDaggerComponent(){
        if (daggerComponent == null) {
            daggerComponent = NotesApplication.getInstance().getAppComponent()
                .addSubComponent(HomeModule(this))

            daggerComponent?.inject(this)
        }
    }

    override fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }
}