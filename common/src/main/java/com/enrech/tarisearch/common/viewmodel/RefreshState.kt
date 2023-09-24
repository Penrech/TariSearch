package com.enrech.tarisearch.common.viewmodel

data class RefreshState(
    var forceRefresh: Boolean = true,
    var isAutoRefreshing: Boolean = false
)