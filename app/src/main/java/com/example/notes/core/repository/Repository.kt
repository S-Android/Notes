package com.example.notes.core.repository

import com.example.notes.core.database.NotesDatabase
import javax.inject.Inject

class Repository @Inject constructor(private val notesDatabase: NotesDatabase)