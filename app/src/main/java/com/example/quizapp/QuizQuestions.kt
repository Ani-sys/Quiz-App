package com.example.quizapp


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding


class QuizQuestions : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQustionsList: ArrayList<Question> = Constants.getQuestions()
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

    private lateinit var binding: ActivityQuizQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        setQuestion()
        setOnClick()

    }

    private fun setOnClick(){
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion(){
        defaultOptionsView()
        val question: Question = mQustionsList!![mCurrentPosition - 1]
        binding.ivFlagImage.setImageResource(question.image)
        binding.pbProgressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition /${binding.pbProgressBar.max}"
        binding.tvQuizQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        binding.tvOptionOne.let {
            options.add(0,it)
        }
        binding.tvOptionTwo.let {
            options.add(1,it)
        }
        binding.tvOptionThree.let {
            options.add(2,it)
        }
        binding.tvOptionFour.let {
            options.add(3,it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.border_background)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#8F53C8"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
                             R.drawable.selected_option_border_bg)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvOptionOne -> {
                binding.tvOptionOne.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tvOptionTwo -> {
                binding.tvOptionTwo.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tvOptionThree -> {
                binding.tvOptionThree.let {
                    selectedOptionView(it,3)
                }
            }
            R.id.tvOptionFour -> {
                binding.tvOptionFour.let {
                    selectedOptionView(it,4)
                }
            }

            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQustionsList.size -> { setQuestion() }
                        else -> {
                            val intent = Intent(this, FinalPage::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQustionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }

                }else{
                    val question = mQustionsList.get(mCurrentPosition-1)
                    if (question.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQustionsList.size){
                        binding.btnSubmit.text = "FINISH"
                    }else{
                        binding.btnSubmit.text = "GO TO THE NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView)
            }
        }
    }
}