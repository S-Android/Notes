package com.example.notes.models.uimodel

data class FolderModel(val name: String) {
    var notes =  HashMap<String, NoteModel>()
}