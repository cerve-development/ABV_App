package com.fair.tool_belt_abv.domain

//class ConvertResultUseCase @Inject constructor() {
//    operator fun invoke(
//        unit: AbvUnit,
//        value: String,
//        previousResult: ConverterResult
//    ): ConverterResult {
//        return when (unit) {
//            AbvUnit.SG -> {
//                val (plato, brix) = Converter.convert(focused = unit.name, value = value)
//                previousResult.copy(sg = value, plato = plato, brix = brix)
//            }
//            AbvUnit.P -> {
//                val (brix, gravity) = Converter.convert(focused = unit.name, value = value)
//                previousResult.copy(plato = value, brix = brix, sg = gravity)
//            }
//            AbvUnit.B -> {
//                val (gravity, plato) = Converter.convert(focused = unit.name, value = value)
//                previousResult.copy(brix = value, sg = gravity, plato = plato)
//            }
//        }
//    }
//}
