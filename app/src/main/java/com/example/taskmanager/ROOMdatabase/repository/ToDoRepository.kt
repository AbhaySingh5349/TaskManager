package com.example.taskmanager.ROOMdatabase.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.ROOMdatabase.DAOInterface
import com.example.taskmanager.ROOMdatabase.models.ToDoTable

// class that abstracts access to multiple data sources (DAO and Network). its best practice for code separation and architecture.
// it provides clean API for data access for rest of application

class ToDoRepository(private val daoInterface: DAOInterface) {

    val getAllData: LiveData<List<ToDoTable>> = daoInterface.getAllData()

    suspend fun insertData(toDoTable: ToDoTable){
        daoInterface.insertData(toDoTable)
    }

    suspend fun updateData(toDoTable: ToDoTable){
        daoInterface.updateData(toDoTable)
    }

    suspend fun deleteData(toDoTable: ToDoTable){
        daoInterface.deleteData(toDoTable)
    }

    suspend fun deleteAll(){
        daoInterface.deleteAll()
    }
}