package com.pucuk.binar_challenge_ch_4.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pucuk.binar_challenge_ch_4.data.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note_table WHERE userId= :userId ORDER BY title DESC")
    suspend fun getAllNotesDesc(userId: Int): List<Note>

    @Query("SELECT * FROM note_table WHERE userId= :userId ORDER BY title ASC")
    suspend fun getAllNotesAsc(userId: Int): List<Note>

    @Query("SELECT * FROM note_table WHERE userId= :userId")
    suspend fun getAllNotes(userId: Int): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}
