package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable

data class EntryDataClass(
    @DrawableRes val icon: Int,
    val title:String,
    val onClick:() -> Unit
)