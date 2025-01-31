package com.example.knowledgemanagement2

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE area = :area")
    suspend fun getNotesByArea(area: String): List<Note>
}
