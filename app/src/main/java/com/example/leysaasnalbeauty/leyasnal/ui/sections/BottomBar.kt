package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText

@Composable
fun BottomBar() {
    NavigationBar(
        Modifier.fillMaxWidth(),
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Home, contentDescription = "") },
            label = { BodyText(stringResource(R.string.home)) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Home, contentDescription = "") },
            label = { BodyText(stringResource(R.string.clients)) }

        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Home, contentDescription = "") },
            label = { BodyText(stringResource(R.string.annotations)) }
        )
    }
}