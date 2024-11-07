package com.example.myapp010aimagetoapp

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp010aimagetoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definice akce pro výběr obrázku
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Skrytí ProgressBar po načítání obrázku
            binding.progressBar.visibility = android.view.View.GONE

            // Pokud nebyl vybrán žádný obrázek
            if (uri != null) {
                binding.ivImage.setImageURI(uri)
            } else {
                Toast.makeText(this, "Nebyl vybrán žádný obrázek.", Toast.LENGTH_SHORT).show()
            }
        }

        // Kliknutí na tlačítko pro výběr obrázku
        binding.btnTakeImage.setOnClickListener {
            // Zobrazení ProgressBar při načítání obrázku
            binding.progressBar.visibility = android.view.View.VISIBLE
            // Spuštění akce pro výběr obrázku
            getContent.launch("image/*")
        }

        // Kliknutí na tlačítko pro otáčení obrázku
        binding.btnRotateImage.setOnClickListener {
            // Animace otáčení o 90 stupňů
            val rotateAnimator = ObjectAnimator.ofFloat(
                binding.ivImage, "rotation", binding.ivImage.rotation + 90f
            )
            rotateAnimator.duration = 300 // Délka animace (v milisekundách)
            rotateAnimator.start()
        }

        // Kliknutí na tlačítko pro resetování rotace
        binding.btnResetRotation.setOnClickListener {
            // Animace pro resetování rotace na 0 stupňů
            val resetAnimator = ObjectAnimator.ofFloat(
                binding.ivImage, "rotation", 0f
            )
            resetAnimator.duration = 300 // Délka animace (v milisekundách)
            resetAnimator.start()
        }
    }
}
