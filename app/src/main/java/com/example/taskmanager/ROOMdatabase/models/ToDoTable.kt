package com.example.taskmanager.ROOMdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass

@Entity(tableName="todo_table")
data class ToDoTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priorityModelClass: PriorityModelClass,
    var description: String
)