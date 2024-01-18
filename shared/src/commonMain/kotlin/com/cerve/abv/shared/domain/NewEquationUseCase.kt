package com.cerve.abv.shared.domain

import com.cerve.abv.shared.util.Equation
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class NewEquationUseCase : KoinComponent {

    suspend operator fun invoke(equation: String?) = flow<String?> {

        val og = 1.09
        val fg = 1.07

        val result = Equation.Calculator(og, fg)
                .custom(equation ?: "")

        emit("og : $og, fg : $fg = $result")

    }.catch { emit(null) }

    companion object {
        fun isValid(equation: String?): Boolean {
            return try {
                val og = 1.09
                val fg = 1.07

                Equation
                    .Calculator(og, fg)
                    .custom(equation!!)

                true
            } catch (_: Exception) {
                false
            }
        }
    }

}