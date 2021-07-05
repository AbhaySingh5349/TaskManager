package com.example.taskmanager.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.taskmanager.R
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass

class SharedViewModel (application: Application) : AndroidViewModel(application) {

    // listener for priority spinner
    val listener : AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }
    }

     fun validateData(title: String, description: String) : Boolean{
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            return false
        }
        return true
    }

    fun parsePriority(prioritySpinner: String): PriorityModelClass {
        return when(prioritySpinner){
            "High Priority" -> {
                PriorityModelClass.HIGH}
            "Medium Priority" -> {
                PriorityModelClass.MEDIUM}
            "LOW Priority" -> {
                PriorityModelClass.LOW}
            else -> {
                PriorityModelClass.LOW}
        }
    }

    fun parsePriorityToInt(priorityModelClass: PriorityModelClass) : Int{
        return when(priorityModelClass){
            PriorityModelClass.HIGH -> 0
            PriorityModelClass.MEDIUM -> 1
            PriorityModelClass.LOW -> 2
        }
    }
}