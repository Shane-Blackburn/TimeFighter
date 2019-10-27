package com.example.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {


    // Create variable that will hold our button and text views
    internal lateinit var tapMeButton: Button
    internal lateinit var scoreText: TextView
    internal lateinit var timeText: TextView

    // Create our initial score variable
    internal var score = 0

    // Create time related variables
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 5000
    internal val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Populate our variables
        tapMeButton = findViewById(R.id.tapMeButton)
        scoreText = findViewById(R.id.scoreTextView)
        timeText = findViewById(R.id.timeRemainingText)


        // Set initial score text
        scoreText.text = getString(R.string.game_score_text, score)


        // Add onClickListener to our button
        tapMeButton.setOnClickListener {
            incrementScore()
        }

        resetGame()
    }


    private fun resetGame() {
        score = 0

        scoreText.text = getString(R.string.game_score_text, score)

        val initialTimeLeft = initialCountDown / 1000
        timeText.text = getString(R.string.time_remaining_text, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeText.text = getString(R.string.time_remaining_text, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }

        gameStarted = false
    }

    private fun incrementScore(){
        if (!gameStarted) {
            startGame()
        }

        score++
        val scoreString = getString(R.string.game_score_text, score)
        scoreText.text = scoreString
    }


    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_finish_toast, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}
