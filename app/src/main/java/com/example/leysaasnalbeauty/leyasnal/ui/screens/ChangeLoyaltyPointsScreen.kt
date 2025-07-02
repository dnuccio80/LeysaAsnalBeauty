package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.helpers.sendPdfFile
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor
import com.itextpdf.io.font.FontProgramFactory
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.AffineTransform
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import java.io.ByteArrayOutputStream
import java.io.File
import java.time.LocalDate


@Composable
fun ChangeLoyaltyPointsScreen(
    viewModel: AppViewModel,
    innerPadding: PaddingValues,
    clientId: Int,
    onBackButtonClicked: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setClientId(clientId)
    }

    val client by viewModel.clientDetails.collectAsState()
    val clientDetailsLoyalty by viewModel.clientDetailsLoyaltyPoints.collectAsState()
    val rewardsLoyalty by viewModel.rewardsLoyalty.collectAsState()
    val context = LocalContext.current
    var changeablePoints by rememberSaveable { mutableIntStateOf(0) }
    var changeableReward by rememberSaveable { mutableStateOf("") }

    if (client == null || client?.id != clientId) return
    if (clientDetailsLoyalty == null) return

    var showConfirmDialog by rememberSaveable { mutableStateOf(false) }

    val filteredLoyaltyList = rewardsLoyalty.filter {
        it.points <= clientDetailsLoyalty!!.points
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BackButtonItem(onBackButtonClicked)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                SecondTitleText(stringResource(R.string.change_points).uppercase())
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkGrayColor
                )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SecondTitleText(client!!.name, color = PositiveColor)
                    ThirdTitleText("${stringResource(R.string.total_points)}: ${clientDetailsLoyalty!!.points}")
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkGrayColor
                )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SecondTitleText(
                        stringResource(R.string.available_changes),
                        color = PositiveColor
                    )
                    if (filteredLoyaltyList.isNullOrEmpty()) {
                        BodyText("No hay ningún canjeable con sus puntos")
                    } else {
                        filteredLoyaltyList.forEach {
                            ChangeRewardRowItem(it.reward, it.points.toString()) {
                                showConfirmDialog = true
                                changeablePoints = it.points
                                changeableReward = it.reward
                            }
                        }
                    }
                }
            }
            AlertDialogItem(
                show = showConfirmDialog,
                text = stringResource(R.string.change_points_alert_dialog),
                onDismiss = { showConfirmDialog = false },
                onConfirm = {
                    showConfirmDialog = false
                    val newLoyaltyPoints = clientDetailsLoyalty!!.points - changeablePoints
                    viewModel.upsertClientPointsLoyalty(
                        clientDetailsLoyalty!!.copy(points = newLoyaltyPoints)
                    )

                    val day = getCompleteDayDate(LocalDate.now().dayOfMonth)
                    val month = getCompleteDayDate(LocalDate.now().monthValue)
                    val year = LocalDate.now().year
                    val currentDate = "$day/$month/$year"
                    val finishLocalDate = LocalDate.now().plusDays(30)
                    val finishDate = "${getCompleteDayDate(finishLocalDate.dayOfMonth)}/${
                        getCompleteDayDate(finishLocalDate.monthValue)
                    }/${finishLocalDate.year}"

                    createPdf(
                        context = context,
                        clientName = client!!.name,
                        startDate = currentDate,
                        finishDate = finishDate,
                        pointsChanged = changeablePoints.toString(),
                        reward = changeableReward
                    )
                    Toast.makeText(context, "Canje realizado con éxito!", Toast.LENGTH_SHORT).show()
                }
            )
        }

    }
}

@Composable
fun ChangeRewardRowItem(title: String, points: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BodyText(title)
        Spacer(Modifier.weight(3f))
        BodyText("$points pts")
        Spacer(Modifier.padding(start = 12.dp))
        ButtonTextItem(
            stringResource(R.string.change),
            DarkAccentColor,
            horizontalPadding = 4.dp,
            verticalPadding = 2.dp
        ) {
            onClick()
        }
    }
}

// Create reward changed pdf & send via WhatsApp
private fun createPdf(
    context: Context,
    clientName: String,
    startDate: String,
    finishDate: String,
    pointsChanged: String,
    reward: String,
) {
    val outputFile = File(context.filesDir, "$clientName Recompensa Canjeada.pdf")

    val pdfWriter = PdfWriter(outputFile)
    val pdfDocument = PdfDocument(pdfWriter)
    val document = Document(pdfDocument, PageSize.A4)
    pdfDocument.addNewPage()

    val canvas = PdfCanvas(pdfDocument.getFirstPage())
    val pageSize = pdfDocument.defaultPageSize
    val pageWidth = pageSize.width
    val pageHeight = pageSize.height

    val imageBytes = drawableToByteArray(context, R.drawable.reward_changed)
    val imageData = ImageDataFactory.create(imageBytes)
    val imageWidth = pageWidth - 20f
    val imageHeight = 200f
    val xPos = (pageWidth - imageWidth) / 2
    val yPos = (pageHeight - imageHeight) / 2

    canvas.addImageWithTransformationMatrix(
        imageData,
        imageWidth,
        0f,
        0f,
        imageHeight,
        xPos,
        yPos,
        false
    )

    val dateFont =
        PdfFontFactory.createFont(StandardFonts.HELVETICA, PdfEncodings.WINANSI, true)

    val assetManager = context.assets

    // Vast Shadow Font
    val vastShadowInputStream = assetManager.open("fonts/vast_shadow.ttf")
    val vastBytes = vastShadowInputStream.readBytes()
    val vastProgram = FontProgramFactory.createFont(vastBytes)
    val pointsFont = PdfFontFactory.createFont(vastProgram, "WinAnsi", true)

    // Raleway Font
    val ralewayInputStream = assetManager.open("fonts/raleway_light.ttf")
    val ralewayBytes = ralewayInputStream.readBytes()
    val ralewayProgram = FontProgramFactory.createFont(ralewayBytes)
    val raleWayFont = PdfFontFactory.createFont(ralewayProgram, "WinAnsi", true)

    // Start Date
    canvas.beginText()
    canvas.setFontAndSize(raleWayFont, 12f)
    canvas.moveText((xPos + 100f).toDouble(), (yPos + 18f).toDouble())
    canvas.showText(startDate)
    canvas.endText()

    // Finish Date
    canvas.beginText()
    canvas.setFontAndSize(raleWayFont, 12f)
    canvas.moveText((xPos + 265f).toDouble(), (yPos + 18f).toDouble())
    canvas.showText(finishDate)
    canvas.endText()

    // Points Changed
    canvas.beginText()
    canvas.setFontAndSize(pointsFont, 32f)
    canvas.moveText((xPos + 395f).toDouble(), (yPos + 110f).toDouble())
    canvas.showText(pointsChanged)
    canvas.endText()


    // Reward
    val paragraph = Paragraph(reward)
        .setFont(raleWayFont)
        .setFontSize(12f)
        .setMultipliedLeading(.9f)
        .setTextAlignment(TextAlignment.CENTER)

    val maxDivWith = dpToPx(context, 50f)
    val div = Div()
        .setWidth(maxDivWith)
        .setMarginTop(420f)
        .setMarginLeft(360f)
        .add(paragraph)

    document.add(div)


    canvas.saveState()

    val fontSize = 10f
    val textWidth = pointsFont.getWidth(clientName, fontSize)
    val rotX = xPos + 550f
    val rotY = (pageHeight / 2f) - (textWidth / 2f)

    val rotation = AffineTransform.getRotateInstance(Math.toRadians(90.0), rotX.toDouble(), rotY.toDouble())
    canvas.concatMatrix(rotation)

    canvas.beginText()
    canvas.setFontAndSize(pointsFont, fontSize)
    canvas.moveText(rotX.toDouble(), rotY.toDouble())
    canvas.showText(clientName)
    canvas.endText()

    canvas.restoreState()
    document.close()

    sendPdfFile(context, outputFile)

}

private fun drawableToByteArray(context: Context, drawableResId: Int): ByteArray {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    val bitmap = (drawable as BitmapDrawable).bitmap

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()

}

private fun dpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}

private fun getCompleteDayDate(date: Int): String {
    return if (date < 10) "0$date"
    else date.toString()
}