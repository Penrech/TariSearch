package com.enrech.tarisearch.common.extension

import android.net.Uri

fun Map<String, Any?>.toStringUri() =
    this.filter { it.value != null }
        .map { (key, value) -> "$key=${Uri.encode(value.toString()) ?: ""}" }
        .joinToString("&")
        .let { if (it.isNotBlank()) "?$it" else "" }

fun <K, V> Map<K, V>.plusNullable(pair: Pair<K, V?>): Map<K, V> = this.let {
    pair.second?.let {
        this.plus(Pair(pair.first, it))
    } ?: it
}

fun <K, V> Map<K, V>.plusNullableOrDefault(pair: Pair<K, V?>, default: V): Map<K, V> = this.let {
    this.plus(Pair(pair.first, pair.second ?: default))
}

fun Map<String, Any?>.plusWithNullable(map: Map<String, Any>?) =
    this.let {
        map?.let { this.plus(map) } ?: this
    }