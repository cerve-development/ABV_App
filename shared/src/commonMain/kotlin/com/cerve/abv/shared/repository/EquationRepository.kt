package com.cerve.abv.shared.repository

import com.cerve.abv.shared.model.AbvTestEquation

interface EquationRepository {
    fun equationList() : List<AbvTestEquation.Entity>
}