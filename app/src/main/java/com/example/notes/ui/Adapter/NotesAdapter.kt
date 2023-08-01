package com.example.notes.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.model.Notes
import com.example.notes.ui.Fragment.HomeFragmentDirections
import java.util.ArrayList

class NotesAdapter(val requireContext: Context, var notes: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val note = notes[position]

        holder.binding.title.text = note.title
        holder.binding.subtitle.text = note.subtitle
        holder.binding.date.text = note.date

        when(note.priority){
            "1" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.greendot)
            }
            "2" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.yellowdot)
            }
            "3" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.reddot)
            }
        }

        holder.binding.root.setOnClickListener{

            // THIS WILL ADD THE ARGUMENTS WHICH WE WANT TO PASS TO OTHER ACTIVITY IN NAVIGATION
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(note)
            Navigation.findNavController(it!!).navigate(action)

        }


    }

    fun filterNotes(myFilteredNotes: ArrayList<Notes>) {
        notes = myFilteredNotes
        notifyDataSetChanged()
    }

}