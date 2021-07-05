package com.example.taskmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import kotlinx.android.synthetic.main.fragment_to_do_list.view.*

class ToDoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)

        view.listFAB.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_addItemFragment) // add new item
        }

        view.listConstraintLayout.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_updateDataFragment) // update existing item
        }

        setHasOptionsMenu(true) // linking menu with fragment

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }
}