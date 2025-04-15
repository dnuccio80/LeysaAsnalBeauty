package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText

@Composable
fun BottomBar() {

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        Modifier.fillMaxWidth(),
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = { Icon(Icons.Filled.Home, contentDescription = "") },
            label = { BodyText(stringResource(R.string.home)) }
        )
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "") },
            label = { BodyText(stringResource(R.string.clients)) }

        )
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { Icon(Icons.Filled.MailOutline, contentDescription = "") },
            label = { BodyText(stringResource(R.string.annotations)) }
        )
    }
}