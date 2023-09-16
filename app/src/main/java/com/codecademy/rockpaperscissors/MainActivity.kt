package com.codecademy.rockpaperscissors

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var rockBtn: ImageButton
    lateinit var paperBtn: ImageButton
    lateinit var scissorBtn: ImageButton
    lateinit var startBtn: Button
    lateinit var playerChoice: ImageView
    lateinit var computerChoice: ImageView
    lateinit var winnerText: TextView
    var rockDrawable: Drawable? = null
    var paperDrawable: Drawable? = null
    var scissorDrawable: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rockBtn = findViewById(R.id.rock_IBtn)
        paperBtn = findViewById(R.id.paper_IBtn)
        scissorBtn = findViewById(R.id.scissors_iBtn)
        startBtn = findViewById(R.id.start_btn)
        playerChoice = findViewById(R.id.player_imgV)
        computerChoice = findViewById(R.id.computer_imgV)
        winnerText = findViewById(R.id.winner_tv)
        rockDrawable = ContextCompat.getDrawable(this, R.drawable.rock)
        paperDrawable = ContextCompat.getDrawable(this, R.drawable.paper)
        scissorDrawable = ContextCompat.getDrawable(this, R.drawable.scissor)

        rockBtn.setOnClickListener {
            it.isSelected = true
            paperBtn.isSelected = false
            scissorBtn.isSelected = false
        }

        paperBtn.setOnClickListener {
            it.isSelected = true
            rockBtn.isSelected = false
            scissorBtn.isSelected = false
        }

        scissorBtn.setOnClickListener {
            it.isSelected = true
            rockBtn.isSelected = false
            paperBtn.isSelected = false
        }

        startBtn.setOnClickListener {
            val computer = getComputerChoice()
            val player = getPlayerChoice()
            getWinner(player = player, computer = computer)
        }
    }

    private fun getPlayerChoice(): String {
        val choice = when {
            rockBtn.isSelected -> "rock"
            paperBtn.isSelected -> "paper"
            scissorBtn.isSelected -> "scissor"
            else -> "Nothing clicked"
        }
        when (choice) {
            "rock" -> playerChoice.setImageDrawable(rockDrawable)
            "scissor" -> playerChoice.setImageDrawable(scissorDrawable)
            else -> playerChoice.setImageDrawable(paperDrawable)
        }
        return choice
    }

    private fun getComputerChoice(): String {
        val list = listOf("rock", "paper", "scissor")
        val choice = list.random()
        when (choice) {
            "rock" -> computerChoice.setImageDrawable(rockDrawable)
            "scissor" -> computerChoice.setImageDrawable(scissorDrawable)
            else -> computerChoice.setImageDrawable(paperDrawable)
        }
        return choice
    }

    private fun getWinner(player: String, computer: String) {
        winnerText.text = when {
            (player == "rock" && computer == "scissor") ||
                    (player == "paper" && computer == "rock") ||
                    (player == "scissor" && computer == "paper") -> "Congratulations, You Have Won!"
            (player == computer) -> "It's a draw!"
            else -> "You Lost!"
        }
    }
}
