package com.example.knowledgemanagement2

import android.content.Context
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                lifecycleScope.launch {
                    val user = userDao.getUserByUsername(username)

                    if (user != null && user.password == password) {
                        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            putString("USER_NAME", user.username)
                            putString("USER_AREA", user.area)
                            apply()
                        }

                        val intent = Intent(this@MainActivity, NoteActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Špatné přihlašovací údaje", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Vyplňte všechna pole", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
