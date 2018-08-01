package com.acmerobotics.splinelib

import kotlin.math.abs

object TestUtil {
    fun numericalDerivative(x: List<Double>, ds: Double): List<Double> {
        val deriv = (0 until x.size - 2).map { (x[it+2] - x[it]) / (2 * ds) }.toMutableList()
        deriv.add(0, deriv[0])
        deriv.add(deriv.last())
        return deriv
    }

    fun compareDerivatives(x: List<Double>, dx: List<Double>, ds: Double, epsilon: Double, errorFreq: Double = 0.01): Boolean {
        val numDx = numericalDerivative(x, ds)
        val count = dx.zip(numDx)
                .map { abs(it.first - it.second) }
                .filter { it > epsilon }
                .count()
        val freq = count.toDouble() / x.size
        return freq < errorFreq
    }
}