package com.aurelioklv.kat.core.data.local.preferences

enum class AppTheme {
    SYSTEM,
    LIGHT,
    DARK;

    companion object {
        fun fromName(name: String): AppTheme {
            return try {
                valueOf(name.uppercase())
            } catch (e: IllegalArgumentException) {
                SYSTEM
            }
        }

        fun getThemeFromOrdinal(ordinal: Int): AppTheme? {
            return AppTheme.values().getOrNull(ordinal)
        }
    }
}
