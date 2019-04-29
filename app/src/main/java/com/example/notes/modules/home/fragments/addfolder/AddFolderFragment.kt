package com.example.notes.modules.home.fragments.addfolder

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

class AddFolderFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_folder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt1.setOnClickListener {
            var databaseReference = FirebaseDatabase.getInstance().getReference()
            databaseReference = databaseReference.child("123").child("folders")

            val folderName = et.text.toString()
            val folder = FirebaseDBFolder(folderName)
//            folder.notes.put("note1", FirebaseDBNote("today need to pick some soup"))
//            folder.notes.put("note2", FirebaseDBNote("play cricket at 8 pm"))

            val id = databaseReference.push().key
            databaseReference.child(id!!).setValue(folder, object: DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    println("xxxx" + p0)
                    println("xxxy" + p1)

                    Thread(Runnable {
                        val folderEntity = FolderEntity()
                        folderEntity.firebaseId = p1.key
                        folderEntity.name = folderName
                        NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(folderEntity)

//                        val noteEntity1 = NoteEntity()
//                        noteEntity1.text = "today need to pick some soup"
//                        noteEntity1.firebaseId = folderEntity.firebaseId
//                        NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(noteEntity1)
//
//                        val noteEntity2 = NoteEntity()
//                        noteEntity2.text = "play cricket at 8 pm"
//                        noteEntity2.firebaseId = folderEntity.firebaseId
//                        NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(noteEntity2)
                    }).start()
                }
            })
        }
    }
}