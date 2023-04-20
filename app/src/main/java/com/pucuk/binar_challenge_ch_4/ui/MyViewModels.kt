package com.pucuk.binar_challenge_ch_4.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pucuk.binar_challenge_ch_4.data.database.AppDatabase
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.data.model.User
import kotlinx.coroutines.launch


class MyViewModels(
    app: Application
) : AndroidViewModel(app) {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun getAllNotesDesc(userId: Int) = viewModelScope.launch {
        _notes.postValue(
            AppDatabase.getInstance((getApplication())).noteDao().getAllNotesDesc(userId)
        )
    }

    fun getAllNotesAsc(userId: Int) = viewModelScope.launch {
        _notes.postValue(
            AppDatabase.getInstance((getApplication())).noteDao().getAllNotesAsc(userId)
        )
    }

    fun getAllNotes(userId: Int) = viewModelScope.launch {
        _notes.postValue(AppDatabase.getInstance((getApplication())).noteDao().getAllNotes(userId))
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        AppDatabase.getInstance((getApplication())).noteDao().insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        AppDatabase.getInstance((getApplication())).noteDao().deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        AppDatabase.getInstance((getApplication())).noteDao().updateNote(note)
    }

    fun insertUser(user: User) = viewModelScope.launch {
        AppDatabase.getInstance((getApplication())).userDao().insert(user)
    }

    fun getUser(username: String, password: String) = viewModelScope.launch {
        val response = AppDatabase.getInstance((getApplication())).userDao().getUser(username, password)
        if (response != null){
            _user.postValue(response)
        }
    }

}