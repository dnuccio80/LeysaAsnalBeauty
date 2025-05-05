package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun SelectableDropdownMenu(list: List<String>, serviceSelected: String, onValueChange: (String) -> Unit) {

    var expandMenu by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.padding(horizontal = 32.dp)) {
        TextField(
            value = serviceSelected,
            modifier = Modifier
                .clickable {
                    expandMenu = !expandMenu
                }
                .fillMaxWidth(),
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = DarkAccentColor
            ),
            placeholder = {
                BodyText("Elegé el servicio...")
            },
            trailingIcon = {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "arrow icon",
                    tint = Color.White
                )
            },
            enabled = false
        )
        DropdownMenu(
            expanded = expandMenu,
            modifier = Modifier.width(250.dp),
            onDismissRequest = { expandMenu = false },
            containerColor = DarkAccentColor
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = { BodyText(it) },
                    onClick = {
                        onValueChange(it)
                        expandMenu = false
                    }
                )
            }
        }
    }
}