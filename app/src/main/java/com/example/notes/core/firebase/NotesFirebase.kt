package com.example.notes.core.firebase

import com.google.firebase.database.FirebaseDatabase

class NotesFirebase {
    var firebaseDatabase: FirebaseDatabase? = null

    init {
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase!!.setPersistenceEnabled(true)
    }

    companion object {
        private var INSTANCE: NotesFirebase? = null
        fun getFirebaseInstance(): NotesFirebase? {
            synchronized(this) {
                if (INSTANCE == null) {
                   INSTANCE = NotesFirebase()
                }
            }
            return INSTANCE
        }
    }
}