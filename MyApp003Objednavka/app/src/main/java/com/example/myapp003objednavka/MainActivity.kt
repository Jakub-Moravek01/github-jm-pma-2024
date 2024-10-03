package com.example.myapp003objednavka

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp003objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        title = "Objednávka kosmetických předmětů"

        //nastavení obsluhy tlačítka "objednat"
        binding.btObjednavka.setOnClickListener {

            //načtení ID vybraného radioButton z radioGroup
            val cosmeticRbId = binding.rgCosmetic.checkedRadioButtonId

            //díky tomuto řádku zobrazíme text a ne ID
            val cosmetic = findViewById<RadioButton>(cosmeticRbId)

            //zjistí, jestli je checkbox zaškrtnutný
            val facNew = binding.cbFactoryNew.isChecked
            val stattrak = binding.cbStattrak.isChecked
            val batSca = binding.cbBattleScared.isChecked

            //předávání hodnot do souhrnu objednávky
            val objednavkaText = "Souhrn objednávky: " + "${cosmetic.text}" + (if(facNew) "; factory new" else "") +
                    (if(stattrak) "; počítadlo pro kamarády" else "") + (if(batSca) "; battle scared" else "")

            binding.tvShrnuti.text = objednavkaText
        }

        //změna obrázku v závislosti na výběru radioButtonu
        binding.rbAWP.setOnClickListener {
            binding.ivSkin.setImageResource(R.drawable.awp)
        }
        binding.rbAK.setOnClickListener {
            binding.ivSkin.setImageResource(R.drawable.ak)
        }
        binding.rbM4.setOnClickListener {
            binding.ivSkin.setImageResource(R.drawable.m4)
        }
    }
}