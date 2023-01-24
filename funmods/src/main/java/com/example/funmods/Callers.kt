package com.example.funmods

import java.math.BigDecimal

fun main() {

    val eleven = BigDecimal.ONE add BigDecimal("10")
    val fifteen = eleven + 4

    val fifteenCouldError = fifteen.assertNonNull()
    val nonNullFifteen = fifteen.assertNonNullOrDefaultWith { BigDecimal("15") }
    val nonNullNoInlineFifteen = fifteen.assertNonNullOrDefaultWithNonInline { BigDecimal("15") }

    val someString = "Kotlin is my favorite programming language"
    val favorite = someString.transformCrossInline { substring(13..20) }
}

fun hasFavorite(string: String): Boolean {
    string.transformCrossInline {
        val result = lowercase()
        if (result.contains("favorite")) {
            return true
        }
        result
    }
    return false
}

fun hasKotlin(string: String): Boolean {
    val lower = string.transformFullInline {
        val result = lowercase()
        if (result.contains("kotlin")) {
            return true
        }
        result
    }
    return false
}