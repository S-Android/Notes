package com.myapp.notesapp.feature_note.domain.use_case

import com.myapp.notesapp.feature_note.domain.repository.AuthenticationRepository

class Authenticate(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(passKey: String): Boolean {
        return repository.authenticate(passKey)
    }
}