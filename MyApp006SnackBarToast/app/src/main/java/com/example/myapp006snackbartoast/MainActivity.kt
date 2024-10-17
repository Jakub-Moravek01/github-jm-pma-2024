package com.example.myapp006snackbartoast

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import android.view.View
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
            val toast = Toast.makeText(this, "Nazdar - mám hlad", Toast.LENGTH_LONG)
            toast.show()
        }

        // Nastavení akce pro tlačítko snackbar
        binding.btnShowSnackBar.setOnClickListener {
           Snackbar.make(binding.root, "Mám hlad", Snackbar.LENGTH_LONG).show()
        }

    }
}