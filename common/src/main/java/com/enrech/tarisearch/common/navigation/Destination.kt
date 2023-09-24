package com.enrech.tarisearch.common.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.enrech.tarisearch.common.extension.plusWithNullable
import com.enrech.tarisearch.common.extension.toStringUri

interface Destination {
    val routeName: String
    val extraParams: Map<String, Any>? get() = null
    val params: Map<String, Any?> get() = mapOf()
    val deepLinkDestination get() = DEEP_LINK_SCHEMA.plus(routeName).initParamsOrBlank()
    val routeDestination get() = routeName.initParamsOrBlank()
    val routeDestinationWithParams get() = routeName.addParamsOrBlank()


    fun getNavArguments(withExtras: Boolean = false) =
        params.let {
            if (withExtras) it.plusWithNullable(extraParams)
            else it
        }.map {
            navArgument(it.key) {
                type = NavType.inferFromValueType(it.value)
                defaultValue = it.value
                nullable = type.isNullableAllowed
            }
        }

    companion object {
        const val DEEP_LINK_SCHEMA = "ts://"
    }

    // adds query params to url or nothing if no params
    fun String.addParamsOrBlank() = plus(
        params
            .plusWithNullable(extraParams)
            .toStringUri()
    )

    fun String.initParamsOrBlank() = plus(
        params
            .plusWithNullable(extraParams)
            .map { (key, _) -> "$key={$key}" }
            .joinToString("&")
            .let { if (it.isNotBlank()) "?$it" else "" }
    )
}