package com.fair.tool_belt_abv.framework.di

import com.fair.tool_belt_abv.ui.viewmodel.CalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.MainViewModel
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    viewModelOf(::EquationCreationViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::CalculatorViewModel)
    viewModelOf(::ConverterViewModel)
}