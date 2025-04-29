package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AnnotationsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.screens.pickPostItColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor

@Composable
fun PostItAnnotationItem(
    item: AnnotationsDataClass,
    modifier: Modifier,
    font: FontFamily,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {

    var showDeleteAlertDialog by rememberSaveable { mutableStateOf(false) }
    val color = remember { pickPostItColor() }

    Box(
        modifier = modifier
            .size(200.dp)
            .background(color)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.TopEnd)
                .size(24.dp)
                .clickable {
                    showDeleteAlertDialog = true
                },
            colors = CardDefaults.cardColors(
                containerColor = NegativeColor,
                contentColor = Color.White
            ),
            shape = CircleShape
        ) {
            Icon(
                Icons.Filled.Close,
                modifier = Modifier.padding(4.dp),
                contentDescription = "delete icon",
                tint = Color.White
            )
        }

        Text(
            item.title, style = TextStyle(
                fontSize = 24.sp,
                fontFamily = font,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        )
    }

    AlertDialogItem(
        show = showDeleteAlertDialog,
        text = stringResource(R.string.delete_annotation_alert_dialog),
        onDismiss = { showDeleteAlertDialog = false },
        onConfirm = {
            showDeleteAlertDialog = false
            onDelete()
        }
    )
}