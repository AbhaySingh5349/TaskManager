package com.example.taskmanager.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass
import com.example.taskmanager.ROOMdatabase.models.ToDoTable
import com.example.taskmanager.ROOMdatabase.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.fragment_add_item.view.*

class AddItemFragment : Fragment() {

    private val toDoViewModel : ToDoViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels() // these methods were to be used again while updating, so we created a common model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_item, container, false)

        setHasOptionsMenu(true) // linking menu with fragment

        view.prioritiesSpinner.onItemSelectedListener = sharedViewModel.listener // listener for adding color to spinner

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu) // display options menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.saveMenu){
            insertDataToDB()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDB() {
        val title = titleTextInputEditText.text.toString()
        val prioritySpinner = prioritiesSpinner.selectedItem.toString()
        val description = descriptionTextInputEditText.text.toString()

        if(sharedViewModel.validateData(title,description)){
            val newData = ToDoTable(
                0,
                title,
                sharedViewModel.parsePriority(prioritySpinner),
                description
            )
            toDoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Task Added Successfully",Toast.LENGTH_SHORT).show()

            // Navigate back
            findNavController().navigate(R.id.action_addItemFragment_to_toDoListFragment)
        }else{
            Toast.makeText(requireContext(),"Enter All Fields",Toast.LENGTH_LONG).show()
        }
    }
}