package com.example.myapplication

object Funciones {
    fun calcularPorcentaje(score: Int, totalQuestions: Int): Int {
        return ((score.toFloat() / totalQuestions.toFloat()) * 100 ).toInt()
    }

    fun mensajeResultado(percentage: Int): String {
        return if (percentage > 60){
            "Felicidades! Has pasado la prueba"
        }else{
            "Mmm! examen reprobado"
        }
    }
}