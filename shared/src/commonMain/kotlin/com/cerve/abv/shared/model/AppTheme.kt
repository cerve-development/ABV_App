package com.cerve.abv.shared.model

enum class AppTheme {

    LEGACY,
    STOUT,
    HOPS,
    LAGER;

    companion object {
        fun tryValueOf(value: String?): AppTheme {
            return try {
                if (value != null) {
                    AppTheme.valueOf(value)
                } else LEGACY
            } catch (_: Exception) {
                LEGACY
            }
        }
        
    }
}
