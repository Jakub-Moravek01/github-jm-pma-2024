package com.example.myapp005moreactivities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val tvInfo = findViewById<TextView>(R.id.tvInfo)

        //Načtení dat z intentu
        val nickname = intent.getStringExtra("NICK_NAME")
        tvInfo.text = "Data z první aktivity: $nickname"

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}