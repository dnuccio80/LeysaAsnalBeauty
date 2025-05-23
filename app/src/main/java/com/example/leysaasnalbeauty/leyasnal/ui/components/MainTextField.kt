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
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    @DrawableRes icon: Int? = null
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
        enabled = enabled,
        label = {
            if (label != null) {
                BodyText(label)
            }
        },
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        placeholder = {
            if (placeholder != null) {
                BodyText(placeholder)
            }
        },
        leadingIcon = {
            if (icon != null) {
                Icon(
                    painterResource(icon),
                    contentDescription = "icon"
                )
            }
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
            disabledTextColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkAccentColor,
            unfocusedIndicatorColor = Color.White,
            disabledContainerColor = Color.DarkGray,
            focusedPlaceholderColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            disabledPlaceholderColor = Color.White,
            errorPlaceholderColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}