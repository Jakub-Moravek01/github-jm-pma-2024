package com.example.myapp02myownlinearlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val etNumber = findViewById<EditText>(R.id.etNumber)
        val tvInformation = findViewById<TextView>(R.id.tvInformation)
        val bntSend = findViewById<Button>(R.id.btnSend)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        bntSend.setOnClickListener {
            val number = etNumber.text.toString().toIntOrNull()
            val result = number % 2

            if (result == 0){
                val formattedText =
                    "Číslo $etNumber je sudé."
                tvInformation.text = formattedText
            } else {
                val formattedText =
                    "Číslo $etNumber je liché."
                tvInformation.text = formattedText
            }



        }

        btnDelete.setOnClickListener {
            etNumber.text.clear()
            tvInformation.text = ""
        }
    }
}