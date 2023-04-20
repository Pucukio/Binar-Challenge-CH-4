package com.pucuk.binar_challenge_ch_4.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val userId: Int
) : Parcelable
