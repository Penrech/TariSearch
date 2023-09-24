package com.enrech.tarisearch.common_domain

abstract class BaseMapper<in From, To> {

    abstract fun mapFrom(from: From): To

    fun mapFromList(from: List<From?>?) = from?.filterNotNull()?.map { mapFrom(it) } ?: emptyList()
}


abstract class BaseReversibleMapper<From, To> {
    abstract fun mapFrom(from: From): To
    abstract fun mapTo(to: To): From

    fun mapFromList(from: List<From?>?) = from?.filterNotNull()?.map { mapFrom(it) } ?: emptyList()
    fun mapToList(to: List<To?>?) = to?.filterNotNull()?.map { mapTo(it) } ?: emptyList()
}