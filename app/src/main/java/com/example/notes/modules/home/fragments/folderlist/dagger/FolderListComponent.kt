package com.example.notes.modules.home.fragments.folderlist.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.modules.home.fragments.folderlist.FolderListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FolderListModule::class])
interface FolderListComponent: BaseComponent {
    fun inject(folderListFragment: FolderListFragment)
}