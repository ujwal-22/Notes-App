package com.example.notes.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.ViewModel.NotesViewModel
import com.example.notes.databinding.FragmentCreateNotesBinding
import com.example.notes.model.Notes
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var  binding: FragmentCreateNotesBinding
    var priority:String = "1"
    val notesViewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.green.setImageResource(R.drawable.done)

        binding.green.setOnClickListener {
            priority = "1"
            binding.green.setImageResource(R.drawable.done)
            binding.yellow.setImageResource(0)
            binding.red.setImageResource(0)
        }
        binding.yellow.setOnClickListener {
            priority = "2"
            binding.yellow.setImageResource(R.drawable.done)
            binding.green.setImageResource(0)
            binding.red.setImageResource(0)
        }
        binding.red.setOnClickListener {
            priority = "3"
            binding.red.setImageResource(R.drawable.done)
            binding.yellow.setImageResource(0)
            binding.green.setImageResource(0)
        }

        binding.buttonSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.title.text.toString()
        val subtitle = binding.subtitle.text.toString()
        val mynote = binding.notes.text.toString()
        val d = Date()
        val notesDate:String = DateFormat.format("MMMM d, yyyy", d.time).toString()

        val notes: Notes = Notes(null, title, subtitle, notesDate, mynote, priority)

        notesViewModel.insertNotes(notes)

        Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)

    }

}