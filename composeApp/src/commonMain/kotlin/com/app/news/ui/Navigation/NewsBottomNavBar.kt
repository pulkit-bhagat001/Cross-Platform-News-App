package com.app.news.ui.Navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.ViewModel
import com.app.news.utils.bottomNavigationList
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavBar(
    index: Int,
    onIndex: (Int) -> Unit
) {
    NavigationBar {
        bottomNavigationList.fastForEachIndexed { i, bottomNavItem ->
            NavigationBarItem(
                selected = index == i,
                onClick = {
                    onIndex(i)
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomNavItem.selectedIcon),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(text = stringResource(bottomNavItem.title)) })
        }
    }

}