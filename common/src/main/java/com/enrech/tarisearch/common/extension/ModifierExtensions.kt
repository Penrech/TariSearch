package com.enrech.tarisearch.common.extension

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

fun Modifier.addIf(predicate: Boolean,  toAdd: @Composable (Modifier) -> Modifier) = composed { if (predicate) this.then(toAdd(this)) else this }

fun Modifier.noBackgroundClickable(onClickLabel: String? = null, onClick: () -> Unit) = composed {
    this.clickable(
        onClickLabel = onClickLabel,
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) { onClick() }
}

fun Modifier.clickableIf(predicate: Boolean, showUIInteraction: Boolean = true, onClickLabel: String? = null, onClick: () -> Unit) =
    composed {
        if (predicate) {
            this.clickable(
                onClickLabel = onClickLabel,
                interactionSource = remember { MutableInteractionSource() },
                indication = if (showUIInteraction) LocalIndication.current else null
            ) { onClick() }
        } else {
            this
        }
    }

fun Modifier.shimmerPlaceholder(
    visible: Boolean,
    color: Color = Color.Unspecified,
    shape: Shape? = null
): Modifier = composed {
    Modifier.placeholder(visible, color, shape, highlight = PlaceholderHighlight.shimmer())
}
