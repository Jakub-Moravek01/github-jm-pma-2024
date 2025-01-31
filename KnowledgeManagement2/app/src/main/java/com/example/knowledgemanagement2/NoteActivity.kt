package com.example.knowledgemanagement2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowledgemanagement2.databinding.ActivityNoteBinding
import kotlinx.coroutines.launch

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var database: AppDatabase
    private lateinit var noteDao: NoteDao
    private val noteList = mutableListOf<Note>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var userArea: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        userArea = sharedPreferences.getString("USER_AREA", "") ?: ""

        database = AppDatabase.getDatabase(this)
        noteDao = database.noteDao()

        noteAdapter = NoteAdapter(
            notes = noteList,
            onDeleteNote = { deleteNote(it) },
            onEditNote = { showEditNoteDialog(it) }
        )

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = noteAdapter

        binding.btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }

        binding.btnLogout.setOnClickListener {
            logoutUser()
        }

        loadNotes()
    }

    private fun loadNotes() {
        lifecycleScope.launch {
            noteList.clear()
            val notes = noteDao.getNotesByArea(userArea)
            noteList.addAll(notes)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteNote(note: Note) {
        lifecycleScope.launch {
            noteDao.deleteNote(note)
            noteList.remove(note)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun showEditNoteDialog(note: Note) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etNoteTitle)
        val etDescription = dialogView.findViewById<EditText>(R.id.etNoteDescription)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSaveNote)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancelNote)

        etTitle.setText(note.title)
        etDescription.setText(note.description)

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnSave.setOnClickListener {
            val updatedTitle = etTitle.text.toString().trim()
            val updatedDescription = etDescription.text.toString().trim()

            if (updatedTitle.isNotEmpty() && updatedDescription.isNotEmpty()) {
                val updatedNote = note.copy(title = updatedTitle, description = updatedDescription)
                lifecycleScope.launch {
                    noteDao.updateNote(updatedNote)
                    loadNotes()
                }
                dialog.dismiss()
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showAddNoteDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etNoteTitle)
        val etDescription = dialogView.findViewById<EditText>(R.id.etNoteDescription)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSaveNote)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancelNote)

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val newNote = Note(title = title, description = description, createdBy = "Neznámý uživatel", area = userArea)
                lifecycleScope.launch {
                    noteDao.insertNote(newNote)
                    loadNotes()
                }
                dialog.dismiss()
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun logoutUser() {
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        // Smazání pouze uživatelských údajů, aby aplikace fungovala správně
        with(sharedPreferences.edit()) {
            remove("USER_NAME")
            remove("USER_AREA")
            apply()
        }

        // Přesměrování na MainActivity s vyčištěním aktivit
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
