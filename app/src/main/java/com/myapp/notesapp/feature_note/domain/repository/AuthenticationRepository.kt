package com.myapp.notesapp.feature_note.domain.repository

interface AuthenticationRepository {
    fun authenticate(passKey: String): Boolean
}