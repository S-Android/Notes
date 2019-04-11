package com.example.notes.modules.home.activity.repository

import com.example.notes.core.database.NotesDatabase
import com.example.notes.core.firebase.NotesFirebase
import com.example.notes.core.repository.NotesRepository
import com.example.notes.core.workmanager.NotesWorkManager
import javax.inject.Inject

class HomeNotesRepository @Inject constructor(override val notesDatabase: NotesDatabase,
                                              override val notesFirebase: NotesFirebase,
                                              override val notesWorkManager: NotesWorkManager)
    : NotesRepository(notesDatabase, notesFirebase, notesWorkManager)