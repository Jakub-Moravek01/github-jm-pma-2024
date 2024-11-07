package com.example.myapp012ajednoduchamatematika

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var TimeTextView: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var PercentageTextView: TextView? = null // TextView pro procentuální skóre
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random: Random = Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calInt = intent.getStringExtra("cals")
        cals = calInt!!
        TimeTextView = findViewById(R.id.TimeTextView)
        QuestionTextText = findViewById(R.id.QuestionTextText)
        ScoreTextView = findViewById(R.id.ScoreTextView)
        AlertTextView = findViewById(R.id.AlertTextView)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        start()
    }

    fun NextQuestion(cal: String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionTextText!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                when (cal) {
                    "+" -> answers.add(a + b)
                    "-" -> answers.add(a - b)
                    "*" -> answers.add(a * b)
                    "/" -> {
                        try {
                            // Ošetření dělení nulou
                            if (b == 0) {
                                answers.add(a)
                            } else {
                                answers.add(a / b)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } else {
                var wrongAnswer = random.nextInt(20)
                try {
                    while (
                        wrongAnswer == a + b
                        || wrongAnswer == a - b
                        || wrongAnswer == a * b
                        || (b != 0 && wrongAnswer == a / b) // Ošetření dělení nulou
                    ) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        btn0!!.text = "${answers[0]}"
        btn1!!.text = "${answers[1]}"
        btn2!!.text = "${answers[2]}"
        btn3!!.text = "${answers[3]}"
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
            points++
            AlertTextView!!.text = "Correct"
        } else {
            AlertTextView!!.text = "Wrong"
        }
        ScoreTextView!!.text = "$points/$totalQuestions"
        NextQuestion(cals)
    }

    fun PlayAgain(view: View?) {
        points = 0
        totalQuestions = 0
        ScoreTextView!!.text = "$points/$totalQuestions"
        countDownTimer!!.start()
    }

    private fun start() {
        NextQuestion(cals)
        countDownTimer = object : CountDownTimer(10000, 500) {
            override fun onTick(p0: Long) {
                TimeTextView!!.text = (p0 / 1000).toString() + "s"
            }

            override fun onFinish() {
                TimeTextView!!.text = "Konec času"
                openDilog()
            }
        }.start()
    }

    private fun openDilog() {
        val inflate = LayoutInflater.from(this)
        var winDialog = inflate.inflate(R.layout.win_layout, null)
        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        PercentageTextView = winDialog.findViewById(R.id.PercentageTextView) // Nastavení pro procento
        var btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        var btnBack = winDialog.findViewById<Button>(R.id.buttonBack)

        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)

        // Výpočet procentuálního skóre
        val percentage = if (totalQuestions > 0) {
            ((points.toDouble() / totalQuestions) * 100).toInt()
        } else {
            0
        }

        FinalScoreTextView!!.text = "$points/$totalQuestions"
        PercentageTextView!!.text = "Procentuální úspěšnost: $percentage%" // Zobrazení procentuálního skóre

        btnPlayAgain.setOnClickListener {
            PlayAgain(it)
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        var showDialog = dialog.create()
        showDialog.show()
    }
}
