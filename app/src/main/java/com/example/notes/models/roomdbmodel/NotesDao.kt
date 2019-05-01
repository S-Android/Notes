package com.example.notes.models.roomdbmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM folders")
    fun getFolders(): LiveData<List<FolderEntity>>

    @Insert
    fun insert(folder: FolderEntity)

    @Query("UPDATE folders SET firebase_id=:firebaseId and is_synced=:isSynced WHERE name = :id")
    fun update(id: String, firebaseId: String, isSynced: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)
}