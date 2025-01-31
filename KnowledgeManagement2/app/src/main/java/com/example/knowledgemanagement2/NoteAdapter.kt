package com.example.knowledgemanagement2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val notes: MutableList<Note>,
    private val onDeleteNote: (Note) -> Unit,
    private val onEditNote: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvNoteTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvNoteDescription)
        val btnEdit: ImageView = view.findViewById(R.id.btnEditNote)
        val btnDelete: ImageView = view.findViewById(R.id.btnDeleteNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvDescription.text = note.description

        holder.btnEdit.setOnClickListener {
            onEditNote(note)
        }

        holder.btnDelete.setOnClickListener {
            onDeleteNote(note)
        }
    }

    override fun getItemCount(): Int = notes.size
}
