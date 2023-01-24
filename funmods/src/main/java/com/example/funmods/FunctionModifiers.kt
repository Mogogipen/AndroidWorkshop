package com.example.funmods

import java.math.BigDecimal

// infix
infix fun BigDecimal.add(other: BigDecimal): BigDecimal {
    return this.add(other)
}

// operator
operator fun BigDecimal.plus(other: Int): BigDecimal {
    return this.add(BigDecimal(other.toString()))
}

inline fun Int.double(other: Int): Int {
    return this.plus(other)
}

// inline with reified
inline fun <reified T: Any> T?.assertNonNull(): T {
    if (this == null) throw NullPointerException()
    return this
}

// inline with lambda
inline fun <reified T: Any> T?.assertNonNullOrDefaultWith(nullDefault: () -> T): T {
    return try {
        this.assertNonNull()
    } catch (e: NullPointerException) {
        nullDefault()
    }
}

// inline with noinline lambda
inline fun <reified T: Any> T?.assertNonNullOrDefaultWithNonInline(noinline doOnNull: () -> T): T {
    return try {
        this.assertNonNull()
    } catch (e: NullPointerException) {
        doOnNull()
    }
}

// inline with crossinline lambda
inline fun <reified T: CharSequence> T?.transformCrossInline(crossinline transformation: String.() -> String): String {
    var result = ""
    this?.doIfNotBlankNotInline {
        result = transformation(this.toString())
    }
    return result
}

inline fun <reified T: CharSequence> T?.transformFullInline(transformation: String.() -> String): String {
    this?.doIfNotBlankInline {
        return transformation(this.toString())
    }
    return ""
}

// Helper
fun CharSequence.doIfNotBlankNotInline(action: () -> Unit) {
    if (this.isNotBlank()) {
        action()
    }
}

inline fun CharSequence.doIfNotBlankInline(action: () -> Unit) {
    if (this.isNotBlank()) {
        action()
    }
}