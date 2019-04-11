package com.example.notes.modules.home.fragments.addfolder.dagger

import com.example.notes.core.base.BaseComponent
import com.example.notes.core.dagger.scopes.FragmentScope
import com.example.notes.modules.home.fragments.addfolder.AddFolderFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [AddFolderModule::class])
interface AddFolderComponent: BaseComponent {
    fun inject(addFolderFragment: AddFolderFragment)
}