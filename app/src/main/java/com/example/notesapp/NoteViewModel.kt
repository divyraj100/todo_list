package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository :NoteRepository
    val allTask : LiveData<List<Note>>

    init {
        val dao =NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allTask = repository.allTask
    }

    fun deleteTask(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertTask(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}