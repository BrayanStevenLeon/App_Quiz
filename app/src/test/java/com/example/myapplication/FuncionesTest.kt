package com.example.myapplication

import org.junit.Assert.*

import org.junit.Test

class FuncionesTest {
    @Test
    fun calcularPorcentaje() {
        assertEquals(50, Funciones.calcularPorcentaje(5, 10))
        assertEquals(100, Funciones.calcularPorcentaje(10, 10))
        assertEquals(0, Funciones.calcularPorcentaje(0, 10))
    }

    @Test
    fun mensajeResultado() {
        assertEquals("Felicidades! Has pasado la prueba", Funciones.mensajeResultado(70))
        assertEquals("Mmm! examen reprobado", Funciones.mensajeResultado(50))
        assertEquals("Mmm! examen reprobado", Funciones.mensajeResultado(0))
    }
}
