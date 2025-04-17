package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.MainColor

@Composable
fun MainTextField(
    value: String,
    isNumeric: Boolean,
    isPhone: Boolean = false,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes icon: Int
) {

    val numberRegex = Regex("^\\d*$")

    TextField(
        value = value,
        onValueChange = {
            if (isPhone || isNumeric) {
                if (it.matches(numberRegex)) {
                    onValueChange(it)
                }
            } else {
                onValueChange(it)
            }
        },
        label = { BodyText(label) },
        singleLine = true,
        leadingIcon = {
            Icon(
                painterResource(icon),
                contentDescription = "money icon",
                tint = Color.White
            )
        },
        keyboardOptions =
            if (isNumeric) {
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            } else {
                KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                )
            },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkAccentColor,
            unfocusedIndicatorColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}