package com.example.mysplash.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.mysplash.ui.SplashUiEvent

@Composable
fun OrderByCard(
    splashUiEvent: (SplashUiEvent) -> Unit
) {
    var expand by remember {
        mutableStateOf(false)
    }
    ElevatedCard(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Welcome to MySplash", textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.weight(1f))
            FilterButton(onFilterClick = {
                expand = !expand
            })
            DropDown(expand, splashUiEvent = splashUiEvent)
        }

    }
}

@Composable
fun FilterButton(
    onFilterClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
//            .padding(10.dp)
            .clickable { onFilterClick() }
    ) {
        Icon(imageVector = Icons.Default.List, contentDescription = "")
        Text(text = "Order By", textAlign = TextAlign.Center)
    }
}

@Composable
fun DropDown(
    expand: Boolean = false,
    splashUiEvent: (SplashUiEvent) -> Unit
) {
    var expanded by remember(expand) {
        mutableStateOf(expand)
    }
    DropdownMenu(
        modifier = Modifier.width(width = 150.dp), expanded = expanded, onDismissRequest = {
            expanded = false
        },
        // adjust the position
        offset = DpOffset(x = (1000.dp), y = 0.dp),
        properties = PopupProperties()
    ) {
        // adding each menu item
        DropdownMenuItem(onClick = {
            expanded = false
            splashUiEvent(SplashUiEvent.ChangeFilter("latest"))
        }, enabled = true, text = {
            Text(text = "Latest")
        }, leadingIcon = {
//            Icon(imageVector = Icons.Default.Settings)
        })
        DropdownMenuItem(onClick = {
            expanded = false
            splashUiEvent(SplashUiEvent.ChangeFilter("oldest"))
        }, enabled = true, text = {
            Text(text = "Oldest")
        }, leadingIcon = {
//            AppIcon(imageVector = Icons.Default.Share)
        })
        DropdownMenuItem(onClick = {
            expanded = false
            splashUiEvent(SplashUiEvent.ChangeFilter("popular"))
        }, enabled = true, text = {
            Text(text = "Popular")
        }, leadingIcon = {
//            AppIcon(imageVector = Icons.Default.Share)
        })
    }
}