package com.example.myapp009asharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp009asharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializace bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //přístup k SharedPreferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        //funkcionalita pro uložení dat
        binding.button1.setOnClickListener {
            val name = binding.etJmeno.text.toString()
            val ageString = binding.etVek.text.toString().trim()

            if (ageString.isBlank()){
                Toast.makeText(this, "Vyplň věk dyk", Toast.LENGTH_SHORT).show()
            }else{
                val age = ageString.toInt()
                val isAdult = binding.checkBox1.isChecked
                if ((age < 18 && isAdult) || (age >= 18 && !isAdult)){
                    Toast.makeText(this, "Kecáš jak kluk buk", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Jasně, dobrá práce a ukládám", Toast.LENGTH_SHORT).show()
                    editor.apply{
                        putString("name", name)
                        putInt("age", age)
                        putBoolean("isAdult", isAdult)
                        apply()
                    }
                }
            }
        }

        //funkcionalita pro načtení dat
        binding.button2.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            binding.etJmeno.setText(name)
            binding.etVek.setText(age)
            binding.checkBox1.isChecked = isAdult
        }
    }
}