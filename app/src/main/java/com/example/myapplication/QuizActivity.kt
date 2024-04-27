package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityQuizBinding
import com.example.myapplication.databinding.ScoreDialogBinding
import kotlin.math.min

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        var questionModelList : List<QuestionModel> = listOf()
        var time : String = ""
    }

    lateinit var binding: ActivityQuizBinding

    var currentQuestionIndex = 0;
    var selectedAnswer = ""
    var score = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        setContentView(binding.root)
        loadQuestions()
        startTimer()
    }
    private fun startTimer(){
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds/60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes,remainingSeconds)
            }

            override fun onFinish() {


            }
        }.start()
    }

    private fun loadQuestions(){
        selectedAnswer = ""
        if (currentQuestionIndex == questionModelList.size){
            finishQuiz()
            return
        }

     binding.apply {
         questionIndicatorTextview.text = "Question ${currentQuestionIndex+1}/ ${questionModelList.size} "
         questionProgressIndicator.progress =
             ( currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100 ).toInt()
         questionTextview.text = questionModelList[currentQuestionIndex].question
         btn0.text = questionModelList[currentQuestionIndex].options[0]
         btn1.text = questionModelList[currentQuestionIndex].options[1]
         btn2.text = questionModelList[currentQuestionIndex].options[2]
         btn3.text = questionModelList[currentQuestionIndex].options[3]
     }
    }

    override fun onClick(view: View?) {
        binding.apply{
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }

        val clickedBtn = view as Button
        if (clickedBtn.id== R.id.next_btn){
            if(selectedAnswer.isEmpty()){
                Toast.makeText(applicationContext,"Porfavor oprime una respuesta para continuar",Toast.LENGTH_SHORT).show()
                return;
            }
            if(selectedAnswer == questionModelList[currentQuestionIndex].correct){
                score++
                Log.i("Score of quiz",score.toString())
            }
            currentQuestionIndex++
            loadQuestions()
        }else{

            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
        }
    }

    private fun finishQuiz(){
        val totalQuestions = questionModelList.size
        val percentage = Funciones.calcularPorcentaje(score, totalQuestions)
        val message = Funciones.mensajeResultado(percentage)

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            scoreTitle.text = message

            if (percentage > 60){
                scoreTitle.setTextColor(Color.BLUE)
            }else{
                scoreTitle.setTextColor(Color.RED)
            }

            scoreSubtitle.text = "$score de $totalQuestions preguntas estan correctas"
            finishBtn.setOnClickListener(){
                finish()
            }
             AlertDialog.Builder(this@QuizActivity)
                .setView(dialogBinding.root)
                .setCancelable(false)
                .show()
        }
    }
}