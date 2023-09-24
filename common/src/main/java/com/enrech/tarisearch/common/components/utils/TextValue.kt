package com.enrech.tarisearch.common.components.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource

@Stable
fun TextValue(text: String): TextValue = TextValueImpl(stringText = text)

@Stable
fun TextValue(@StringRes textId: Int, ): TextValue = TextValueImpl(resTextId = textId)

@Immutable
internal class TextValueImpl(
    private val stringText: String? = null,
    private val resTextId: Int? = null
): TextValue {

    override fun getRawValue(): Any =
        stringText ?: resTextId ?: com.enrech.ulessontest.common_resources.R.string.empty

    @Composable
    override fun invoke(): String =
        when {
            stringText != null -> stringText
            resTextId != null -> stringResource(id = resTextId)
            else -> stringResource(id = com.enrech.ulessontest.common_resources.R.string.empty)
        }

    @Composable
    override fun invoke(vararg args: Any): String =
        when {
            resTextId != null && args.isNotEmpty() -> stringResource(id = resTextId, args)
            else -> invoke()
        }
}

@Stable
interface TextValue {
    @Composable
    operator fun invoke(): String

    @Composable
    operator fun invoke(vararg args: Any): String

    fun getRawValue(): Any

    companion object {
        @Stable
        fun Empty(): TextValue = TextValueImpl()
    }
}