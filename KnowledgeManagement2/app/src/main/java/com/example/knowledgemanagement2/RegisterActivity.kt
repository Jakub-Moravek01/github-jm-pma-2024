package com.example.knowledgemanagement2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.knowledgemanagement2.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace bindingu
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace databáze a DAO
        database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        // Nastavení Spinneru s oblastmi A, B, C
        val areas = arrayOf("A", "B", "C")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, areas)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerArea.adapter = spinnerAdapter

        // Listener na tlačítko Registrace
        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val area = binding.spinnerArea.selectedItem.toString()

            // Kontrola, zda jsou pole vyplněná
            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Vyplňte všechna pole", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kontrola, zda hesla souhlasí
            if (password != confirmPassword) {
                Toast.makeText(this, "Hesla se neshodují", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Uložení uživatele do Room databáze
            lifecycleScope.launch {
                val existingUser = userDao.getUserByUsername(username)
                if (existingUser != null) {
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Uživatelské jméno již existuje", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val user = User(
                        firstName = firstName,
                        lastName = lastName,
                        username = username,
                        email = email,
                        password = password,
                        area = area
                    )
                    userDao.insertUser(user)

                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Registrace úspěšná", Toast.LENGTH_SHORT).show()
                        // Přesměrování na přihlašovací obrazovku
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}
