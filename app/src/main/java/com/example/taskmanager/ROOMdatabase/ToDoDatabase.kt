package com.example.taskmanager.ROOMdatabase

import android.content.Context
import androidx.room.*
import com.example.taskmanager.ROOMdatabase.models.ToDoTable

@Database(entities = [ToDoTable:: class], version = 1, exportSchema = false)
@TypeConverters(Converter:: class)
abstract class ToDoDatabase: RoomDatabase(){
    abstract fun daoInterface() : DAOInterface

    // same as public static final class in JAVA, allows to get reference of our functions to other classes
    companion object{
        @Volatile // writes to this field are immediately visible to other threads
        private var INSTANCE : ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){ // we already have instance of our database
                return tempInstance
            }

            // when thread call 'synchronized', it acquires 'lock' of that block ans other threads do not have permission to call that thread,
            // hence multiple threads will not create multiple instances
            synchronized(this){
                // creating new instance of our database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase:: class.java,
                    "todo_database"
                ).build();
                INSTANCE = instance
                return instance
            }
        }
    }
}