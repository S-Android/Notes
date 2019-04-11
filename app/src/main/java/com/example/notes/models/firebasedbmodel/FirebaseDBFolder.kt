package com.example.notes.models.firebasedbmodel

data class FirebaseDBFolder(val name: String) {
    constructor() : this("")
    var notes =  HashMap<String, FirebaseDBNote>()
}