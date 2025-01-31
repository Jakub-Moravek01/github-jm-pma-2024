package com.example.knowledgemanagement2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.knowledgemanagement2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace databáze a DAO
        database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        // Odkaz na registraci
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Přihlášení
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Zkontrolujeme, jestli uživatel existuje v databázi
            lifecycleScope.launch {
                val user = userDao.getUserByUsername(username)

                if (user != null && user.password == password) {
                    // Pokud uživatel existuje a heslo je správné, zobrazíme toast
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Přihlášení úspěšné", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Pokud nejsou shody, ukážeme chybovou hlášku
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Chybné přihlašovací údaje", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
