package com.example.taskmanager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.taskmanager.R
import com.example.taskmanager.ROOMdatabase.models.PriorityModelClass
import com.example.taskmanager.ROOMdatabase.models.ToDoTable
import com.example.taskmanager.ROOMdatabase.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_update_data.*
import kotlinx.android.synthetic.main.fragment_update_data.view.*

class UpdateDataFragment : Fragment() {

    private val args by navArgs<UpdateDataFragmentArgs>() // to receive data from List Adapter
    private val sharedViewModel : SharedViewModel by viewModels()
    private val toDoViewModel : ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_update_data, container, false)

        setHasOptionsMenu(true)

        // current item is name give to argument in Navigation Graph
        view.titleTextInputEditText.setText(args.currentItem.title)
        view.updateDescriptionTextInputEditText.setText(args.currentItem.description)
        view.updatePrioritiesSpinner.setSelection(sharedViewModel.parsePriorityToInt(args.currentItem.priorityModelClass))
        view.updatePrioritiesSpinner.onItemSelectedListener = sharedViewModel.listener

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.updateMenu -> updateItem()
            R.id.deleteMenu -> deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = titleTextInputEditText.text.toString()
        val description = updateDescriptionTextInputEditText.text.toString()
        val getPriority = updatePrioritiesSpinner.selectedItem.toString()

        val validation = sharedViewModel.validateData(title,description)
        if(validation){
            val updatedItem = ToDoTable(
                args.currentItem.id,
                title,
                sharedViewModel.parsePriority(getPriority),
                description
            )
            toDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(),"Task Updated Successfully", Toast.LENGTH_SHORT).show()

            // Navigate back
            findNavController().navigate(R.id.action_updateDataFragment_to_toDoListFragment)
        }else{
            Toast.makeText(requireContext(),"Enter All Fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Delete Task"){_,_ ->
            toDoViewModel.deleteData(args.currentItem)
            Toast.makeText(requireContext(),"'${args.currentItem.title}': Deleted Successfully",Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_updateDataFragment_to_toDoListFragment)
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete : '${args.currentItem.title}'")
        builder.setMessage("Are you sure you want to remove the task : '${args.currentItem.title}' ?")
        builder.create().show()
    }
}