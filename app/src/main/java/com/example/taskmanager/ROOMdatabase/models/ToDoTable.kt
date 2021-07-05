package com.example.taskmanager.ROOMdatabase.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName="todo_table")
@Parcelize
data class ToDoTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priorityModelClass: PriorityModelClass,
    var description: String
) : Parcelable