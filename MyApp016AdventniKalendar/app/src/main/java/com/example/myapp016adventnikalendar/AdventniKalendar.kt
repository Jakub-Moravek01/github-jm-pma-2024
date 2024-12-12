package com.example.myapp016adventnikalendar

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp016adventnikalendar.databinding.ActivityAdventniKalendarBinding
import com.example.myapp016adventnikalendar.databinding.ActivityMainBinding

class AdventniKalendar : AppCompatActivity() {
    private lateinit var binding: ActivityAdventniKalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityAdventniKalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Generování 24 tlačítek pro jednotlivé dny
        for (i in 1..24) {
            val button = Button(this).apply {
                text = i.toString()  // Text odpovídá dni v kalendáři
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                setPadding(16, 16, 16, 16)
            }

            // Přidání tlačítka do GridLayoutu
            val params = GridLayout.LayoutParams().apply {
                width = 0  // Umožní roztáhnout na šířku sloupce
                height = GridLayout.LayoutParams.WRAP_CONTENT
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Flexibilní řádky
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Flexibilní sloupce
            }
            button.layoutParams = params

            // Přidání tlačítka do GridLayout
            binding.gridLayout.addView(button)
        }

        // Nastavení tlačítka pro návrat na MainActivity
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Zavře aktuální aktivitu
        }
    }
}