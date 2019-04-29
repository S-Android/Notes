package com.example.notes.models.roomdbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var text: String? = null

    @ColumnInfo(name = "firebase_id")
    var firebaseId: String? = null

    @ColumnInfo(name = "parent_firebase_id")
    var parentFirebaseId: String? = null

    @ColumnInfo(name = "is_synced")
    var isSynced: Boolean? = null

    @ColumnInfo(name = "is_deleted")
    var isDeleted: Boolean? = null
}