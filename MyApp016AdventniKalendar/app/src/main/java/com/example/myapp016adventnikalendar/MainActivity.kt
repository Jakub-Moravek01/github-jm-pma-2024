package com.example.myapp016adventnikalendar

import android.content.Intent
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
            // Otevře AdventniKalendar aktivitu
            val intent = Intent(this, AdventniKalendar::class.java)
            startActivity(intent)
        }

        binding.btnExitApp.setOnClickListener {
            finishAffinity() // Zavře všechny aktivity a skutečně ukončí aplikaci
        }

    }
}
