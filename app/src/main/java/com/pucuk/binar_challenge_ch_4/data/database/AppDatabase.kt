package com.pucuk.binar_challenge_ch_4.data.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pucuk.binar_challenge_ch_4.data.dao.NoteDao
import com.pucuk.binar_challenge_ch_4.data.dao.UserDao
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.data.model.User

@Database(entities = [Note::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "noteapp.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
