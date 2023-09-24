package com.enrech.tarisearch.common.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager

@SuppressLint("ComposableNaming")
@Composable
fun KeyboardActions.Companion.DismissOnDone(
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onGo: (KeyboardActionScope.() -> Unit)? = null,
    onNext: (KeyboardActionScope.() -> Unit)? = null,
    onPrevious: (KeyboardActionScope.() -> Unit)? = null,
    onSearch: (KeyboardActionScope.() -> Unit)? = null,
    onSend: (KeyboardActionScope.() -> Unit)? = null
): KeyboardActions {
    val localFocus = LocalFocusManager.current

    return KeyboardActions(
        onDone = {
            localFocus.clearFocus()
            onDone?.let { it() }
        },
        onGo = {
            localFocus.clearFocus()
            onGo?.let { it() }
        },
        onSend = {
            localFocus.clearFocus()
            onSend?.let { it() }
        },
        onNext = onNext,
        onPrevious = onPrevious,
        onSearch = onSearch
    )
}