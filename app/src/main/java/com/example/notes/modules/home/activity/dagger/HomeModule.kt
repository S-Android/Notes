package com.example.notes.modules.home.activity.dagger

import androidx.lifecycle.ViewModelProvider
import com.example.notes.NotesApplication
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.core.dagger.scopes.ActivityScope
import com.example.notes.core.database.NotesDatabase
import com.example.notes.core.firebase.NotesFirebase
import com.example.notes.core.viewmodel.ViewModelProviderFactory
import com.example.notes.core.workmanager.NotesWorkManager
import com.example.notes.modules.home.activity.HomeActivity
import com.example.notes.modules.home.activity.repository.HomeNotesRepository
import com.example.notes.modules.home.activity.viewmodel.HomeViewModel
import com.example.notes.modules.home.fragments.folderlist.FolderListFragment
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val homeActivity: HomeActivity) {
    @ActivityScope
    @ActivityLevelFactoryProvider
    @Provides
    fun provideViewModelFactory(viewModel: HomeViewModel):  ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

    @ActivityScope
    @Provides
    fun provideHomeViewModel(application: NotesApplication, repository: HomeNotesRepository): HomeViewModel {
        return HomeViewModel(application, repository)
    }

    @ActivityScope
    @Provides
    fun provideRepository(notesDatabase: NotesDatabase, notesFirebase: NotesFirebase, notesWorkManager: NotesWorkManager): HomeNotesRepository {
        return HomeNotesRepository(notesDatabase, notesFirebase, notesWorkManager)
    }

    @Provides
    fun provideFolderListFragment(): FolderListFragment {
        return FolderListFragment()
    }
}