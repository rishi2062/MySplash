package com.example.mysplash.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SplashImageCard(
    image: String,
    description: String
) {
    ElevatedCard(onClick = { /*TODO*/ },
        modifier = Modifier.padding(10.dp)) {
        RemoteImage( imageLink = image )
        Text(text = description, modifier = Modifier.padding(10.dp))
    }
}

