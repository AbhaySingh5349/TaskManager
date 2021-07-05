package com.example.taskmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.taskmanager.R

class AddItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_item, container, false)

        setHasOptionsMenu(true) // linking menu with fragment

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }
}