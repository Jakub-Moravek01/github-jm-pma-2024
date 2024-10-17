package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivityMainBinding
import com.example.myapp005moreactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        //binding settings
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Druhá aktivita"

        //val tvInfo = findViewById<TextView>(R.id.tvInfo)

        //Načtení dat z intentu
        val nickname = intent.getStringExtra("NICK_NAME")
        binding.tvInfo.text = "Data z první aktivity: $nickname"


        //poslání textu z tvInfo do třetí aktivity
        binding.btnThird.setOnClickListener {
            val info = binding.tvInfo.text.toString() // získání textu z pole
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("INFO", info)
            startActivity(intent)

        }

        //val btnBack = findViewById<Button>(R.id.btnBack)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}