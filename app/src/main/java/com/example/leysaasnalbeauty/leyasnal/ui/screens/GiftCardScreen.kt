package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Document
import java.io.ByteArrayOutputStream
import java.io.File

@Composable
fun GiftCardScreen(innerPadding: PaddingValues) {

    var giftName by rememberSaveable { mutableStateOf("") }
    var toName by rememberSaveable { mutableStateOf("") }
    var fromName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }


    LaunchedEffect(giftName, toName, fromName, phoneNumber) {
        isButtonEnabled = fieldsAreNotEmpty(giftName, toName, fromName, phoneNumber)
    }

    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FirstTitleText("Crear Gift Card")
            HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Gray)
            ThirdTitleText("Completa los datos para crear tu gift card")
            Spacer(Modifier.size(4.dp))
            MainTextField(
                value = giftName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { giftName = it },
                label = stringResource(R.string.gift_type),
                icon = R.drawable.ic_gift
            )
            MainTextField(
                value = toName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { toName = it },
                label = stringResource(R.string.gift_to),
                icon = R.drawable.ic_name
            )
            MainTextField(
                value = fromName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { fromName = it },
                label = stringResource(R.string.gift_from),
                icon = R.drawable.ic_flower
            )
            MainTextField(
                value = phoneNumber,
                isNumeric = true,
                isPhone = true,
                onValueChange = { phoneNumber = it },
                label = stringResource(R.string.gift_phone_number),
                icon = R.drawable.ic_smartphone
            )
            Spacer(Modifier.size(32.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(
                    text = stringResource(R.string.gift_generate),
                    enabled = isButtonEnabled,
                    buttonColor = AccentColor
                ) {
                    createPdf(context, giftName, toName, fromName)
                }
            }
        }
    }
}


// Check if fields are not empty
private fun fieldsAreNotEmpty(
    giftType: String,
    toName: String,
    fromName: String,
    phoneNumber: String
): Boolean {
    return giftType.isNotEmpty() && toName.isNotEmpty() && fromName.isNotEmpty() && phoneNumber.isNotEmpty()
}

private fun createPdf(
    context: Context,
    giftName: String,
    toName: String,
    fromName: String,
) {
    val outputFile = File(context.filesDir, "$toName Gift Card.pdf")

    val pdfWriter = PdfWriter(outputFile)
    val pdfDocument = PdfDocument(pdfWriter)
    val document = Document(pdfDocument, PageSize.A4)
    pdfDocument.addNewPage()

    val canvas = PdfCanvas(pdfDocument.getFirstPage())
    val pageSize = pdfDocument.defaultPageSize
    val pageWidth = pageSize.width

    val backImageBytes = drawableToByteArray(context, R.drawable.gift_card_back)
    val backImageData = ImageDataFactory.create(backImageBytes)
    val imageWidth = 480f
    val imageHeight = 350f
    val backX = (pageWidth - imageWidth) / 2
    val backY = 50f
    canvas.addImageWithTransformationMatrix(backImageData, imageWidth, 0f, 0f, imageHeight, backX, backY, false)

    val imageBytes = drawableToByteArray(context, R.drawable.gift_card_front)
    val imageData = ImageDataFactory.create(imageBytes)
    val frontX = (pageWidth - imageWidth) / 2
    val frontY = backY + imageHeight + 30f
    canvas.addImageWithTransformationMatrix(imageData, imageWidth, 0f, 0f, imageHeight, frontX, frontY, false)

    val font =
        PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC, PdfEncodings.WINANSI, true)

    // Gift Type
    canvas.beginText()
    canvas.setFontAndSize(font, 16f)
    canvas.moveText((backX + 150f).toDouble(), (backY + 183f).toDouble())
    canvas.showText(giftName)
    canvas.endText()

    // To
    canvas.beginText()
    canvas.setFontAndSize(font, 16f)
    canvas.moveText((backX + 115f).toDouble(), (backY + 143f).toDouble())
    canvas.showText(toName)
    canvas.endText()

    // From
    canvas.beginText()
    canvas.setFontAndSize(font, 16f)
    canvas.moveText((backX + 180f).toDouble(), (backY + 103f).toDouble())
    canvas.showText(fromName)
    canvas.endText()

    document.close()

}

fun drawableToByteArray(context: Context, drawableResId: Int): ByteArray {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    val bitmap = (drawable as BitmapDrawable).bitmap

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()

}