package com.app.news.ui.Setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.app.news.utils.Theme
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.app_name
import newsapp.composeapp.generated.resources.apply
import newsapp.composeapp.generated.resources.cancel
import newsapp.composeapp.generated.resources.choose_a_theme
import newsapp.composeapp.generated.resources.delete
import newsapp.composeapp.generated.resources.delete_bookmark
import newsapp.composeapp.generated.resources.delete_bookmark_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeleteBookmarkDialog(
    onDeleteBookMark: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(Res.string.delete_bookmark))
        },
        text = {
            Text(text = stringResource(Res.string.delete_bookmark_description))

        },
        icon = {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = "", tint = MaterialTheme.colorScheme.error)
        },
        confirmButton = {
            TextButton(onClick = {onDeleteBookMark()}){
                Text(text = stringResource(Res.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest()}){
                Text(text = stringResource(Res.string.cancel))
        }}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    onThemeChanged:(Theme)->Unit,
    onDismissRequest: () -> Unit,
    currentTheme: String
) {
    var currentSelectedTheme by remember { mutableStateOf(Theme.valueOf(currentTheme)) }
    BasicAlertDialog(
        onDismissRequest=onDismissRequest,
        content = {
            Surface(
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(stringResource(Res.string.choose_a_theme), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(10.dp))
                    Theme.entries.fastForEach {
                        Row(modifier = Modifier.fillMaxWidth().padding(start = 5.dp).clickable { currentSelectedTheme=it }, horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = currentSelectedTheme==it,
                                onClick = {currentSelectedTheme=it})
                            Text(text = stringResource(it.title), style = MaterialTheme.typography.titleMedium)

                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = {onDismissRequest()}){
                            Text(stringResource(Res.string.cancel), style = MaterialTheme.typography.labelLarge)
                        }
                        TextButton(onClick = {onThemeChanged(currentSelectedTheme)}){
                            Text(stringResource(Res.string.apply), style = MaterialTheme.typography.labelLarge)
                        }

                    }


                }
            }


        }
    )
}