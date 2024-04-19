package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
            getDataFromFirebase()
    }

    private fun setupRecyclerView(){

        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase(){
        //data

        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("qué es android", mutableListOf("Language","OS","Producto","Ninguna"),"OS"))
        listQuestionModel.add(QuestionModel("quién es el dueño de android", mutableListOf("Apple","Google","Samsung","Microsoft"),"Google"))
        listQuestionModel.add(QuestionModel("qué asistente utiliza android", mutableListOf("Siri","Cortana","Asistente de Google","Alexa"),"Asistente de google"))


        quizModelList.add(QuizModel("1","Programacion","toda la programación básica","10",listQuestionModel))
       /* quizModelList.add(QuizModel("2","Computacion","preguntas sobre informática","20"))
        quizModelList.add(QuizModel("3","Geografia","Aumente sus conocimientos geográficos","15")) */
        setupRecyclerView()
    }
}