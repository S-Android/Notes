package com.myapp.notesapp.presentation.authentication

import androidx.compose.ui.focus.FocusState
import com.myapp.notesapp.presentation.add_edit_note.AddEditNoteEvent

sealed class AuthenticationUiEvent {
    data class ShowSnackBar(val message: String): AuthenticationUiEvent()
    object PinSuccess: AuthenticationUiEvent()
}