package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.example.quizapp.databinding.ActivityEnterYourNameBinding


class Username : AppCompatActivity() {
    private lateinit var binding: ActivityEnterYourNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterYourNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            onClickedStart()
        }

}
    private fun onClickedStart(){
        if (binding.etEnterYourName.text.toString().isEmpty()){
            binding.etEnterYourName.setBackgroundResource(R.drawable.red_border_bg)
            binding.tvErrorText.text = "*Please enter your name"
        } else {
            val intent = Intent(this, QuizQuestions::class.java)
            intent.putExtra(Constants.USER_NAME, binding.etEnterYourName.text.toString())
            startActivity(intent)}

    }
}