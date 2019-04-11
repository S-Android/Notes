package com.example.notes.modules.home.activity.viewmodel

import com.example.notes.NotesApplication
import com.example.notes.core.base.BaseViewModel
import com.example.notes.modules.home.activity.repository.HomeNotesRepository

class HomeViewModel (private val application: NotesApplication, val repository: HomeNotesRepository) : BaseViewModel(application) {

}