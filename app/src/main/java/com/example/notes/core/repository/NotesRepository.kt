package com.example.notes.core.repository

import com.example.notes.core.database.NotesDatabase
import com.example.notes.core.firebase.NotesFirebase
import com.example.notes.core.workmanager.NotesWorkManager
import javax.inject.Inject

open class NotesRepository @Inject constructor(protected open val notesDatabase: NotesDatabase,
                                               protected open val notesFirebase: NotesFirebase,
                                               protected open val notesWorkManager: NotesWorkManager)