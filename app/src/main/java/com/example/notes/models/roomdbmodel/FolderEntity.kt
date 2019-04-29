package com.example.notes.models.roomdbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
class FolderEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "firebase_id")
    var firebaseId: String? = null

    @ColumnInfo(name = "is_synced")
    var isSynced: Boolean? = null

    @ColumnInfo(name = "is_deleted")
    var isDeleted: Boolean? = null
}