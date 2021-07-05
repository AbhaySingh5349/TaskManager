package com.example.taskmanager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.ROOMdatabase.viewmodel.ToDoViewModel
import com.example.taskmanager.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_to_do_list.view.*

class ToDoListFragment : Fragment() {

    private val listAdapter : ListAdapter by lazy { ListAdapter() }
    private val toDoViewModel : ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)

        val listRecyclerView = view.listRecyclerView
        listRecyclerView.adapter = listAdapter
        listRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        toDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            table->listAdapter.setData(table) // whenevr new data is added, we will be notofies and update our recycler view
        })

        view.listFAB.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_addItemFragment) // add new item
        }

        setHasOptionsMenu(true) // linking menu with fragment

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllMenu -> deleteAllItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItems() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Delete Task"){_,_ ->
            toDoViewModel.deleteAll()
            Toast.makeText(requireContext(),"All Tasks Deleted Successfully",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete All Tasks")
        builder.setMessage("Are you sure you want to remove all the task ?")
        builder.create().show()
    }
}