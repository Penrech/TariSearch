package com.enrech.tarisearch.common_domain.extension

/**
 iOS ported handy extensions to handle multiple nullables
 **/

inline fun <R: Any, T : Any, Y : Any> ifLet(a: T?, b: Y?, closure: (it1: T, it2: Y) -> R): R? =
    if (a != null && b != null) {
        closure(a, b)
    } else null

inline fun <R: Any, T : Any, Y : Any, X : Any> ifLet(
    a: T?,
    b: Y?,
    c: X?,
    closure: (it1: T, it2: Y, it3: X) -> R
): R? =
    if (a != null && b != null && c != null) {
        closure(a, b, c)
    } else null

inline fun <R: Any, T : Any, Y : Any, X : Any, Z : Any> ifLet(
    a: T?,
    b: Y?,
    c: X?,
    d: Z?,
    closure: (it1: T, it2: Y, it3: X, it4: Z) -> R
): R? =
    if (a != null && b != null && c != null && d != null) {
        closure(a, b, c, d)
    } else null

inline fun <R: Any, T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> R): R? =
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    } else null

fun Any?.toStringOr(nullText: String): String = this?.toString() ?: nullText

