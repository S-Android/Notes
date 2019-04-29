package com.example.notes.modules.home.fragments.addNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.notes.R
import com.example.notes.core.database.NotesDatabase
import com.example.notes.models.firebasedbmodel.FirebaseDBFolder
import com.example.notes.models.firebasedbmodel.FirebaseDBNote
import com.example.notes.models.roomdbmodel.FolderEntity
import com.example.notes.models.roomdbmodel.NoteEntity
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_folder.*

class AddNoteFragment: DialogFragment() {
    var parentFirebaseId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt1.setOnClickListener {
            var databaseReference = FirebaseDatabase.getInstance().getReference()
            databaseReference = databaseReference.child("123").child("folders").child(parentFirebaseId!!).child("notes")

            val noteText = et.text.toString()
            val note = FirebaseDBNote(noteText)

            val id = databaseReference.push().key
            databaseReference.child(id!!).setValue(note, object: DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    println("xxxx" + p0)
                    println("xxxy" + p1)

                    Thread(Runnable {
                        val noteEntity = NoteEntity()
                        noteEntity.text = noteText
                        noteEntity.firebaseId = p1.key
                        noteEntity.parentFirebaseId = parentFirebaseId
                        NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(noteEntity)
                    }).start()
                }
            })
        }
    }
}