package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.AnnotationsDetailsComponent
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText

@Composable
fun AnnotationsDetailsScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    annotationId: Int,
    onBackButtonClicked: () -> Unit,
    onConfirmButtonClicked: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setAnnotationId(annotationId)
    }

    val annotation by viewModel.annotationDetails.collectAsState()

    if (annotation == null || annotation!!.id != annotationId) return

    var title by rememberSaveable { mutableStateOf(annotation!!.title) }
    var description by rememberSaveable { mutableStateOf(annotation!!.description) }

    AnnotationsDetailsComponent(
        innerPadding = innerPadding,
        title = title,
        description = description,
        onConfirmButtonClicked = {
            if (title.isNotEmpty() && description.isNotEmpty()) {
                onConfirmButtonClicked()
                viewModel.updateAnnotation(
                    annotation!!.copy(
                        title = title,
                        description = description
                    )
                )
            }
        },
        onBackButtonClicked = { onBackButtonClicked() },
        onTitleChange = { title = it },
        onDescriptionChange = { description = it }
    )
}

