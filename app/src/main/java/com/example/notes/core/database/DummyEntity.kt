package com.example.notes.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class DummyEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var text: String? = null
}