package com.enrech.tarisearch.common.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enrech.tarisearch.common_resources.theme.TariSearchTheme
import com.enrech.tarisearch.common_resources.theme.accentRed

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    isRequestLoading: Boolean,
    onSearch: (value: String) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val clearVisible by remember {
        derivedStateOf {
            query.isNotBlank()
        }
    }

    Row(
        modifier
            .clip(RoundedCornerShape(100))
            .shadow(AppBarDefaults.TopAppBarElevation)
            .background(accentRed)
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = query,
            placeholder = {
                Text(
                    text = stringResource(id = com.enrech.ulessontest.common_resources.R.string.search_box_placeholder),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            singleLine = true,
            onValueChange = { query = it },
            trailingIcon = {
                if (clearVisible) {
                    IconButton(onClick = { query = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White.copy(alpha = 0.6f),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
            shape = RoundedCornerShape(100)
        )
        if (isRequestLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(8.dp).size(24.dp),
                strokeWidth = 3.dp,
                color = Color.White,
                strokeCap = StrokeCap.Round
            )
        } else {
            IconButton(
                enabled = query.isNotEmpty(),
                onClick = { onSearch(query) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xff00000)
@Composable
private fun SearchBoxPreview() {
    TariSearchTheme {
        SearchBox(isRequestLoading = false) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xff00000)
@Composable
private fun SearchBoxLoadingPreview() {
    TariSearchTheme {
        SearchBox(isRequestLoading = true) {}
    }
}