package com.cerve.abv.shared.model

enum class AbvUnit {
    SG,
    Plato,
    Brix;

    companion object {
        fun tryValueOf(value: String?): AbvUnit {
            return try {
                if (value != null) {
                    valueOf(value)
                } else SG
            } catch (_: Exception) {
                SG
            }
        }
    }

}
