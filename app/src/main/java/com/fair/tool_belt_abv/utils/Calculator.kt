package com.fair.tool_belt_abv.utils

import java.text.DecimalFormat


object Calculator {

    fun String?.sCalculator(os:String?, fs:String?): Triple<String, String, String> {

            Equation.apply{

                return when {

                    os.isNullOrEmpty() or fs.isNullOrEmpty() or this@sCalculator.isNullOrEmpty() -> {
                        Triple("0","0", "(Approximate ABV)")
                    }

                    else -> {
                        val warning = groupWarning(os as String, fs as String,"SG") //todo tweak warning
                        if (warning.first) {

                            val df = DecimalFormat("#.##")
                            val fAttenuation = df.format(aCalculator(os.toDouble(),fs.toDouble()))

                            when(this@sCalculator) {
                                "S" -> Triple(df.format(simpleE(os.toDouble(), fs.toDouble())), fAttenuation, warning.second)
                                else -> Triple(df.format(advancedE(os.toDouble(), fs.toDouble())),fAttenuation, warning.second)
                            }

                        } else {
                            Triple("0","0", warning.second)
                        }
                    }
                }

        }


    }

    fun String?.pCalculator(op:String?, fp:String?): Triple<String, String, String> {

        Equation.apply{

            return when {
                op.isNullOrEmpty() or fp.isNullOrEmpty() or this@pCalculator.isNullOrEmpty() -> {
                    Triple("0","0", "(Approximate ABV)")
                }

                else -> {
                    val warning = groupWarning(op as String, fp as String,"P") //todo tweak warning
                    if (warning.first) {

                        val cSp = pToS(op.toDouble())
                        val cFp = pToS(fp.toDouble())

                        val df = DecimalFormat("#.##")
                        val fAttenuation = df.format(aCalculator(cSp,cFp))

                        when(this@pCalculator){
                            "S" -> Triple(df.format(simpleE(cSp, cFp)), fAttenuation, warning.second)
                            else -> Triple(df.format(advancedE(cSp, cFp)), fAttenuation, warning.second)
                        }
                    } else {
                        Triple("0","0", warning.second)
                    }
                }
            }

        }

    }

    fun String?.bCalculator(ob:String?, fb:String?): Triple<String, String, String> {

        Equation.apply{

            return when {
                ob.isNullOrEmpty() or fb.isNullOrEmpty() or this@bCalculator.isNullOrEmpty() -> {
                    Triple("0","0", "(Approximate ABV)")
                }

                else -> {
                    val warning = groupWarning(ob as String, fb as String,"B")
                    if (warning.first) {
                        val cOb = bToS(ob.toDouble())
                        val cFb = bToS(fb.toDouble())

                        val df = DecimalFormat("#.##")
                        val fAttenuation = df.format(aCalculator(cOb,cFb))

                        when(this@bCalculator){
                            "S" -> Triple(df.format(simpleE(cOb,cFb)), fAttenuation, warning.second)
                            else -> Triple(df.format(advancedE(cOb,cFb)),fAttenuation, warning.second)
                        }
                    }else{Triple("0","0", warning.second)}


                }
            }

        }

    }

    private fun groupWarning(original: String, final: String, unitType: String): Pair<Boolean,String> {

        val myUnit = rangeSelection(unitType)

        return when {
            original.toDouble() < final.toDouble() -> Pair(false, "(Your original can't be lower than your final)")
            original.toDouble() > myUnit.second -> Pair(false, "(Your original shouldn't be higher than ${myUnit.second})")
            original.toDouble() < myUnit.first -> Pair(false, "(Your original shouldn't be lower than ${myUnit.first})")
            final.toDouble() > myUnit.second -> Pair(false, "(Your final shouldn't be higher than ${myUnit.second})")
            final.toDouble() < myUnit.first -> Pair(false, "(Your final shouldn't be lower than ${myUnit.first})")
            else -> Pair(true, "(Approximate ABV)")

        }

    }

    private fun rangeSelection(unit: String): Pair<Double, Double> {
        return when(unit){
        "B" -> Pair(0.0, 40.0)
        "P" -> Pair(0.0000, 39.9490)
        "SG" -> Pair(1.0000, 1.1790)
        else -> Pair(0.0, 0.0)
    }

}

}