package com.example.notes.ui.Fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.ViewModel.NotesViewModel
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.Notes
import com.example.notes.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val notesViewModel:NotesViewModel by viewModels()
    var myAllNotes = arrayListOf<Notes>()
    lateinit var adapter:NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        notesViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->

            myAllNotes = notes as ArrayList<Notes>
            binding.allnotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(), notes)
            binding.allnotes.adapter = adapter

        }

        binding.allNotes.setOnClickListener {

            notesViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->
                myAllNotes = notes as ArrayList<Notes>
                binding.allnotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notes)
                binding.allnotes.adapter = adapter
            }

        }

        binding.red.setOnClickListener {

            notesViewModel.getHighNotes().observe(viewLifecycleOwner) { notes ->
                myAllNotes = notes as ArrayList<Notes>
                binding.allnotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notes)
                binding.allnotes.adapter = adapter
            }

        }
        binding.yellow.setOnClickListener {

            notesViewModel.getMediumNotes().observe(viewLifecycleOwner) { notes ->
                myAllNotes = notes as ArrayList<Notes>
                binding.allnotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notes)
                binding.allnotes.adapter = adapter
            }

        }
        binding.green.setOnClickListener {

            notesViewModel.getLowNotes().observe(viewLifecycleOwner) { notes ->
                myAllNotes = notes as ArrayList<Notes>
                binding.allnotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notes)
                binding.allnotes.adapter = adapter
            }

        }

        // THIS WILL REDIRECT USER TO ANOTHER ACTIVITY
        binding.buttonAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)      // THIS ID IS TAKEN FROM "nav_graph"
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView: SearchView? = menuItem?.actionView as SearchView?
        searchView?.queryHint = "Enter Notes Here..."
        searchView?.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterNotes(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.d("ujwal","EFef")
//
//        return super.onOptionsItemSelected(item)
//    }

    private fun filterNotes(newText: String?) {

        val myFilteredNotes = arrayListOf<Notes>()
        for(i in myAllNotes){
            if(i.title!!.contains(newText!!) || i.subtitle!!.contains(newText!!)){
                myFilteredNotes.add(i)
            }
        }
        adapter.filterNotes(myFilteredNotes)
    }

}