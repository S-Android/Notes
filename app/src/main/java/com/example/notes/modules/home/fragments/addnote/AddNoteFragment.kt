package com.example.notes.modules.home.fragments.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.notes.R
import com.example.notes.core.database.NotesDatabase
import com.example.notes.models.firebasedbmodel.FirebaseDBNote
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
            var databaseReference = FirebaseDatabase.getInstance().reference
            databaseReference = databaseReference.child("123").child("folders").child(parentFirebaseId!!).child("notes")

            val noteText = et.text.toString()
            val note = FirebaseDBNote(noteText)

            Thread(Runnable {
                val noteEntity = NoteEntity()
                noteEntity.text = noteText
                noteEntity.parentFirebaseId = parentFirebaseId
                NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(noteEntity)

                val id = databaseReference.push().key
                databaseReference.child(id!!).setValue(note) { p0, p1 ->
                    println("xxxx$p0")
                    println("xxxy$p1")

                    Thread(Runnable {
                        NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().updateNote(noteText, p1.key!!, parentFirebaseId!!, true)
                    }).start()
                }
            }).start()


        }
    }
}