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

    @ColumnInfo(name = "folder_id")
    var folderId: String? = null
}