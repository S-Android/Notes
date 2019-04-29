package com.example.notes.models.uimodel

data class FolderModel(val name: String) {
    lateinit var firebaseId: String
    var notes =  ArrayList<NoteModel>()
}