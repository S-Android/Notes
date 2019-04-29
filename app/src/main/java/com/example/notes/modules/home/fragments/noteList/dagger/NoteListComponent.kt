package com.example.notes.modules.home.fragments.noteList.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.modules.home.fragments.noteList.NoteListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [NoteListModule::class])
interface NoteListComponent: BaseComponent {
    fun inject(noteListFragment: NoteListFragment)
}