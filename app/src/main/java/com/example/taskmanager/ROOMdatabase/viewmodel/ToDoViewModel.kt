package com.example.taskmanager.ROOMdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ROOMdatabase.ToDoDatabase
import com.example.taskmanager.ROOMdatabase.models.ToDoTable
import com.example.taskmanager.ROOMdatabase.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// communication centre between repository and UI

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val daoInterface = ToDoDatabase.getDatabase(application).daoInterface()
    private val toDoRepository: ToDoRepository

    val getAllData : LiveData<List<ToDoTable>> // hold all data from database and fragment will observe this field

    init{
        toDoRepository = ToDoRepository(daoInterface)
        getAllData = toDoRepository.getAllData
    }

    fun insertData(toDoTable: ToDoTable){
        // we are running background thread (coroutine) to run insertData function
        viewModelScope.launch ( Dispatchers.IO ){
            toDoRepository.insertData(toDoTable)
        }
    }

    fun updateData(toDoTable: ToDoTable){
        // we are running background thread (coroutine) to run updateData function
        viewModelScope.launch ( Dispatchers.IO ){
            toDoRepository.updateData(toDoTable)
        }
    }

    fun deleteData(toDoTable: ToDoTable){
        // we are running background thread (coroutine) to run updateData function
        viewModelScope.launch ( Dispatchers.IO ){
            toDoRepository.deleteData(toDoTable)
        }
    }

    fun deleteAll(){
        // we are running background thread (coroutine) to run updateData function
        viewModelScope.launch ( Dispatchers.IO ){
            toDoRepository.deleteAll()
        }
    }
}