package com.example.notes.models.uimodel

data class NoteModel(val note: String) {
    lateinit var firebaseId: String
}