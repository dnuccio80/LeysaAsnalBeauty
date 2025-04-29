package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AnnotationsDetailsComponent

@Composable
fun AddAnnotationScreen(
    innerPadding: PaddingValues,
    onConfirmButtonClicked: (String, String) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    AnnotationsDetailsComponent(
        innerPadding = innerPadding,
        title = title,
        description = description,
        onConfirmButtonClicked = {
            if(title.isNotEmpty() && description.isNotEmpty()) {
                onConfirmButtonClicked(title, description)
            }
        },
        onBackButtonClicked = { onBackButtonClicked() },
        onTitleChange = { title = it },
        onDescriptionChange = { description = it }
    )

}