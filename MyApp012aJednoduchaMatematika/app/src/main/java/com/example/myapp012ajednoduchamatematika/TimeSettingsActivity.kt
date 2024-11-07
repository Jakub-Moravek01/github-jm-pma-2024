package com.example.myapp012ajednoduchamatematika

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimeSettingsActivity : AppCompatActivity() {

    private lateinit var timeSeekBar: SeekBar
    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private var timeLimit: Long = 10 // Defaultní čas (10 sekund)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_settings)

        timeSeekBar = findViewById(R.id.timeSeekBar)
        timeTextView = findViewById(R.id.timeTextView)
        startButton = findViewById(R.id.startButton)

        // Inicializace seekbaru
        timeSeekBar.max = 60 // Maximální časový limit (60 sekund)
        timeSeekBar.progress = 10 // Výchozí hodnota (10 sekund)
        timeTextView.text = "Čas: 10 s"

        // Nastavení změny na seekbaru
        timeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                timeLimit = progress.toLong() * 1000 // Převod na milisekundy
                timeTextView.text = "Čas: $progress s"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Spuštění hry s vybraným časem
        startButton.setOnClickListener {
            val calInt = Intent(this@TimeSettingsActivity, MainActivity::class.java)
            calInt.putExtra("cals", "+")  // Předání operace (+, -, *, /)
            calInt.putExtra("timeLimit", timeLimit) // Předání časového limitu
            startActivity(calInt)
        }
    }
}
