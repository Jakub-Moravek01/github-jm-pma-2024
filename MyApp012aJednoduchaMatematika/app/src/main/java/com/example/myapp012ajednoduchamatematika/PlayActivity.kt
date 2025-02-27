package com.example.myapp012ajednoduchamatematika

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val addition = findViewById<ImageView>(R.id.addition)
        val sub = findViewById<ImageView>(R.id.sub)
        val multi = findViewById<ImageView>(R.id.multi)
        val division = findViewById<ImageView>(R.id.division)

        addition.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "+")  // Předání operace
            startActivity(calInt)
        }

        sub.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "-")  // Předání operace
            startActivity(calInt)
        }

        multi.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "*")  // Předání operace
            startActivity(calInt)
        }

        division.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "/")  // Předání operace
            startActivity(calInt)
        }
    }
}
