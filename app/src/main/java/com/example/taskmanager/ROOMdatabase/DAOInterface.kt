package com.example.taskmanager.ROOMdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.ROOMdatabase.models.ToDoTable

@Dao
interface DAOInterface {

    // data wrapped in LiveData object ( we will be able to observe data changes of LiveData object from fragments )
    @Query("SELECT * FROM todo_table ORDER BY id ASC" )
    fun getAllData() : LiveData<List<ToDoTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if we re-enter same data, we will not insert it again
    suspend fun insertData(toDoTable: ToDoTable) // 'suspend' keyword used to tell compiler that function will run inside coroutines
}