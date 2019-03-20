package com.example.notes.core.dagger.components

import com.example.notes.NotesApplication
import com.example.notes.core.dagger.builders.ActivityBuilder
import com.example.notes.core.dagger.modules.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface ApplicationComponent: AndroidInjector<NotesApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<NotesApplication>()
}