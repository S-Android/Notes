package com.myapp.notesapp.presentation.util

sealed class Screen(val route: String) {
    object AuthenticationScreen: Screen("authentication_screen")
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}
