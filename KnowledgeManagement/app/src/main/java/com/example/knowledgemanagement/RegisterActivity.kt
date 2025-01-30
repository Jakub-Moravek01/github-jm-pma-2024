package com.example.knowledgemanagement

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowledgemanagement.databinding.ActivityRegisterBinding // Ujisti se, že je import správný

class RegisterActivity : AppCompatActivity() {

    // Vytvoříme objekt pro binding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Spinner s možnostmi A, B, C
        val areas = arrayOf("A", "B", "C")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, areas)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerArea.adapter = spinnerAdapter

        // Tlačítko pro registraci - kliknutí
        binding.btnRegister.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            // Kontrola, zda se hesla shodují
            if (password != confirmPassword) {
                Toast.makeText(this, "Hesla se neshodují", Toast.LENGTH_SHORT).show()
            } else {
                // Pokračování s logikou registrace (například ukládání dat, volání API)
                val firstName = binding.etFirstName.text.toString()
                val lastName = binding.etLastName.text.toString()
                val username = binding.etUsername.text.toString()
                val email = binding.etEmail.text.toString()
                val area = binding.spinnerArea.selectedItem.toString()

                // Příklad: zobrazíme potvrzení
                Toast.makeText(this, "Registrace probíhá pro $firstName $lastName v oblasti $area", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
