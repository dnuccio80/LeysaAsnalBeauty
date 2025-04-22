package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.CursiveText
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun GiftCardScreen(buyType: String, to: String, from: String, innerPadding: PaddingValues) {

    val context = LocalContext.current
    var bitmap by rememberSaveable { mutableStateOf<Bitmap?>(null) }

    Box(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black)
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CreateGiftCard(buyType = buyType, to = to, from = from)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(
                    text = stringResource(R.string.share_whatsapp),
                    buttonColor = AccentColor,
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            bitmap = withContext(Dispatchers.IO) {
                                captureComposableToBitmap(context) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                    ) {
                                        Image(painterResource(R.drawable.gift_card_back), contentDescription = null)
                                        CursiveText(buyType, 140.dp, (-14).dp)
                                        CursiveText(to, 110.dp, 20.dp)
                                        CursiveText(from, 160.dp, 55.dp)
                                    }
                                }
                            }

                            bitmap?.let {
                                shareBitmapViaWhatsapp(context, it, context.getString(R.string.gift_card_wpp_message), phoneNumber = "3571590020")
                            }
                        }
                    }
                )
            }
        }
    }
}

fun captureComposableToBitmap(
    context: Context,
    content: @Composable () -> Unit
): Bitmap {
    val composeView = ComposeView(context).apply {
        setContent { content() }
        measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.AT_MOST)
        )
        layout(0, 0, measuredWidth, measuredHeight)
    }

    return composeView.drawToBitmap()
}

fun shareBitmapViaWhatsapp(context: Context, bitmap: Bitmap, message: String, phoneNumber: String) {
    val file = File(context.cacheDir, "gift_card_back.webp")
    FileOutputStream(file).use { stream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    }

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, message)
        setPackage("com.whatsapp")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun CreateGiftCard(buyType: String, to: String, from: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.gift_card_back),
            contentDescription = "back gift card",
            contentScale = ContentScale.FillWidth
        )
        CursiveText(buyType, 140.dp, (-14).dp)
        CursiveText(to, 110.dp, 20.dp)
        CursiveText(from, 160.dp, 55.dp)
    }
}


