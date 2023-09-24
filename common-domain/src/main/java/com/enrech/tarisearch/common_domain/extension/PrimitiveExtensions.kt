package com.enrech.tarisearch.common_domain.extension

val String.Companion.Empty get() = ""

val Int.Companion.Unspecified get() = -1

val Long.Companion.Unspecified get() = -1L

val Float.Companion.Unspecified get() = -1f

val Double.Companion.Unspecified get() = -1.0

fun Float.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
fun String?.containsAny(keywords: List<String>): Boolean {
    if (this == null) return false
    for (keyword in keywords) {
        if (this.contains(keyword, true)) return true
    }
    return false
}