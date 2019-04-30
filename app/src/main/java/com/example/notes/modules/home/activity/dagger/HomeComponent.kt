package com.example.notes.modules.home.activity.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.scopes.ActivityScope
import com.example.notes.modules.home.activity.HomeActivity
import com.example.notes.modules.home.fragments.folderlist.dagger.FolderListComponent
import com.example.notes.modules.home.fragments.folderlist.dagger.FolderListModule
import com.example.notes.modules.home.fragments.notelist.dagger.NoteListComponent
import com.example.notes.modules.home.fragments.notelist.dagger.NoteListModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent: BaseComponent {
    fun inject(homeActivity: HomeActivity)
    fun addSubComponent(folderListModule: FolderListModule): FolderListComponent
    fun addSubComponent(noteListModule: NoteListModule): NoteListComponent
}