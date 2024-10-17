package com.example.myapp006snackbartoast

import android.app.ActionBar
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp006snackbartoast.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //binding nastavení
    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()

        // inicializace bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Nastavení akce pro tlačítko toast
        binding.btnShowToast.setOnClickListener {
            // Inflate the custom layout
            val inflater = layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, null)

            // Set the text for the toast
            val textView: TextView = layout.findViewById(R.id.toast_text)
            textView.text = "Nazdar - mám hlad"

            // Ensure the TextView is visible
            textView.visibility = View.VISIBLE

            // Create and show the toast
            val toast = Toast(this)
            toast.duration = Toast.LENGTH_LONG
            toast.view = layout
            toast.show()
        }






        // Nastavení akce pro tlačítko snackbar
        binding.btnShowSnackBar.setOnClickListener {
           Snackbar.make(binding.root, "Mám hlad", Snackbar.LENGTH_LONG)
               .setDuration(5000)
               .setBackgroundTint(Color.parseColor("#FF6853"))
               .setAction("Zavřít") {
                   Toast.makeText(this, "Zavírám SnackBar", Toast.LENGTH_LONG).show()
               }
               .show()
        }

    }
}