package com.example.myapp005moreactivities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivitySecondBinding
import com.example.myapp005moreactivities.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        //binding settings
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Třetí aktivita"

        val info = intent.getStringExtra("INFO")
        binding.tvInfo2.text = "Data z druhé aktivity: $info"

        binding.btnBack2.setOnClickListener {
            finish()
        }
    }
}