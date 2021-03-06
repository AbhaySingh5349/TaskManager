package com.example.taskmanager.ROOMdatabase

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass

// room databse allows conversion in between 'primitive data-types' (like int, string, float, bool) but does not allow custom objects (like priority)
// hence we need to have functions to convert 1. Priority object -> String (while writing on DB)
//                                            2. String -> Priority object (while reading from DB)

class Converter {

    @TypeConverter
    fun fromPriority(priorityModelClass: PriorityModelClass) : String{
        return priorityModelClass.name // press ctrl+q to find usage
    }

    @TypeConverter
    fun toPriority(priorityString: String) : PriorityModelClass {
        return PriorityModelClass.valueOf(priorityString) // press ctrl+q to find usage
    }

}