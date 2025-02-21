package com.app.news.ui.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBar(
   value:String,
   onValueChange:(String)->Unit,
   onSearch:(String)->Unit

) {
    val focusManager= LocalFocusManager.current
TextField(
    value=value,
    onValueChange = {
        onValueChange(it)
    },
    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
    textStyle = MaterialTheme.typography.bodyLarge.copy(
       fontWeight =  FontWeight.SemiBold
    ),
    leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "") },
    placeholder = { Text(text = stringResource(Res.string.search), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold) },
    shape = MaterialTheme.shapes.medium,
    singleLine = true,
    keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search
    ),
    keyboardActions = KeyboardActions (
        onSearch = {
            focusManager.clearFocus()
            onSearch(value)

        }

    )
)
}