package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R

@Composable
fun EditIcon(size: Dp, icon: Int = R.drawable.ic_edit, onClick: () -> Unit) {
    Icon(
        painterResource(icon),
        contentDescription = "edit icon",
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .clickable {
                onClick()
            }
    )
}