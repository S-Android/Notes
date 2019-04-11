package com.example.notes.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DummyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: String)
}