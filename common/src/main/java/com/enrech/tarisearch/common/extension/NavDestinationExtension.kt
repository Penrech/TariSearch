package com.enrech.tarisearch.common.extension

import androidx.navigation.NavDestination

val NavDestination.routeName get() = this.route?.let { it.split(Separator).firstOrNull() ?: this.route }

private const val Separator = "?"