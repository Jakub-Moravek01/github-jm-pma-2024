package com.example.myapp016adventnikalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp016adventnikalendar.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nastavení aktuálního data
        val currentDate = SimpleDateFormat("d. MMMM yyyy", Locale.getDefault()).format(Date())
        binding.tvCurrentDate.text = "Datum: $currentDate"

        // Nastavení akcí na tlačítka
        binding.btnOpenCalendar.setOnClickListener {
            // Logika pro otevření kalendáře
        }
        binding.btnExitApp.setOnClickListener {
            finish() // Zavře aplikaci
        }
    }
}
