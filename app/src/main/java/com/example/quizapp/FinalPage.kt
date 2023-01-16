package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityFinalPageBinding

class FinalPage : AppCompatActivity() {
    private lateinit var binding:ActivityFinalPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

            clickedOnHome()
            clickedOnPlayAgain()
            getInfo()

    }

    private fun getInfo(){
        binding.tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

    }

    private fun clickedOnPlayAgain(){
        binding.tvPlay.setOnClickListener {
            val intent = Intent(this, QuizQuestions::class.java)
            startActivity(intent)
        }
    }

    private fun clickedOnHome(){
        binding.tvHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}