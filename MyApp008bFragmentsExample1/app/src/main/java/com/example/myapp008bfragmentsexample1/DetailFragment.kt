package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewAuthor: TextView
    private lateinit var imageViewObrazek: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        textViewTitle = view.findViewById(R.id.textViewTitle)
        textViewAuthor = view.findViewById(R.id.textViewAuthor)
        imageViewObrazek = view.findViewById(R.id.imageViewObrazek)

        // Načtení argumentů a aktualizace textových polí
        arguments?.let {
            val title = it.getString("title")
            val author = it.getString("author")
            val description = it.getString("description")

            updateDetails(title ?: "Unknown", author ?: "Unknown")

            // Nastavení obrázku z drawable
            imageViewObrazek.setImageResource(R.drawable.ak) // Pokud chcete nahradit, změňte na jiný obrázek
        }

        return view
    }


    // Metoda pro aktualizaci zobrazení detailů
    fun updateDetails(title: String, author: String) {
        textViewTitle.text = title
        textViewAuthor.text = author
    }
}