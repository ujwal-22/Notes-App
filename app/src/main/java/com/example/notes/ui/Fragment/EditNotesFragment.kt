package com.example.notes.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.ViewModel.NotesViewModel
import com.example.notes.databinding.FragmentEditNotesBinding
import com.example.notes.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    lateinit var binding : FragmentEditNotesBinding
    val note by navArgs<EditNotesFragmentArgs>()
    var priority:String = "1"
    val notesViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    requireActivity().onBackPressed()
                }
            }
        )

        binding.title.setText(note.note.title)
        binding.subtitle.setText(note.note.subtitle)
        binding.notes.setText(note.note.note)
        when(note.note.priority){
            "1" -> {
                priority = "1"
                binding.green.setImageResource(R.drawable.done)
            }
            "2" -> {
                priority = "2"
                binding.yellow.setImageResource(R.drawable.done)
            }
            "3" -> {
                priority = "3"
                binding.red.setImageResource(R.drawable.done)
            }
        }

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
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.title.text.toString()
        val subtitle = binding.subtitle.text.toString()
        val mynote = binding.notes.text.toString()
        val d = Date()
        val notesDate:String = DateFormat.format("MMMM d, yyyy", d.time).toString()


        // THE ID IS PASSED BECAUSE WE WANT TO UPDATE THE EXISTING NOTES
        val notes: Notes = Notes(note.note.id, title, subtitle, notesDate, mynote, priority)

        notesViewModel.updateNotes(notes)

        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete){

            var clickedview =  view
            val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheetDialog.setContentView(R.layout.delete_dialog)
            bottomSheetDialog.show()

            val textViewYes = bottomSheetDialog.findViewById<TextView>(R.id.yes)
            val textViewNo = bottomSheetDialog.findViewById<TextView>(R.id.no)

            textViewYes?.setOnClickListener{
                notesViewModel.deleteNotes(note.note.id)
                bottomSheetDialog.dismiss()
                Navigation.findNavController(clickedview!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
            }

            textViewNo?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}