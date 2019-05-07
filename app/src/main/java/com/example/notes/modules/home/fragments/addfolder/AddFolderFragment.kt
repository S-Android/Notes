package com.example.notes.modules.home.fragments.addfolder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.notes.R
import com.example.notes.core.database.NotesDatabase
import com.example.notes.models.firebasedbmodel.FirebaseDBFolder
import com.example.notes.models.roomdbmodel.FolderEntity
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_folder.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class AddFolderFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_folder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt1.setOnClickListener {
            val folderName = et.text.toString()
            val folder = FirebaseDBFolder(folderName)

            Thread(Runnable {
                val folderEntity = FolderEntity()
                folderEntity.name = folderName
                folderEntity.isSynced = false
                NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().insert(folderEntity)

                // remove this and ask work manager to do this
                var databaseReference = FirebaseDatabase.getInstance().getReference()
                databaseReference = databaseReference.child("123").child("folders")
                val id = databaseReference.push().key
                databaseReference.child(id!!).setValue(folder, object: DatabaseReference.CompletionListener {
                    override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                        println("xxxx" + p0)
                        println("xxxy" + p1)

                        Thread(Runnable {
                            NotesDatabase.getDatabase(activity!!.applicationContext).getNotesDao().update(folderName, p1.key!!, true)
                        }).start()
                    }
                })
            }).start()
        }
    }
}