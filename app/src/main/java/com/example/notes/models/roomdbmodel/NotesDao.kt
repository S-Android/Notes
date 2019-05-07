package com.example.notes.models.roomdbmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM folders")
    fun getFolders(): LiveData<List<FolderEntity>>

    @Insert
    fun insert(folder: FolderEntity)

    @Query("UPDATE folders SET firebase_id = :firebaseId, is_synced = :isSynced WHERE name = :folderName")
    fun update(folderName: String, firebaseId: String, isSynced: Boolean)


    @Query("SELECT * FROM notes WHERE parent_firebase_id = :parentFirebaseId")
    fun getNotes(parentFirebaseId: String): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Query("UPDATE notes SET firebase_id = :firebaseId, parent_firebase_id = :parentFirebaseId, is_synced = :isSynced WHERE name = :noteText")
    fun updateNote(noteText: String, firebaseId: String, parentFirebaseId: String, isSynced: Boolean)
}