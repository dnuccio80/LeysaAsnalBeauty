package com.example.leysaasnalbeauty.leyasnal.ui.helpers

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import java.net.URLEncoder
import androidx.core.net.toUri
import java.io.File

interface WhatsAppSender {
    fun sendMessage(context: Context, phoneNumber: String, message: String)
    fun sendPdf(context: Context, pdfFile: File)
}

class WhatsAppSenderImplementation : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
        try {
            val uri =
                "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.whatsapp")
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun sendPdf(context: Context, pdfFile: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            pdfFile
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setPackage("com.whatsapp")
        }
        try {
            context.startActivity(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                if (pdfFile.exists()) {
                    val deleted = pdfFile.delete()
                    if (!deleted) {
                        Log.w("GiftCard", "Failed to delete PDF file: ${pdfFile.absolutePath}")
                    }
                }
            }, 15000)

        } catch (e: Exception) {
            Toast.makeText(context, "Failed to share PDF via WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
}

class WhatsAppBusinessSenderImplementation : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
        try {
            val uri =
                "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.whatsapp.w4b")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
    override fun sendPdf(context: Context, pdfFile: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            pdfFile
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setPackage("com.whatsapp.w4b")
        }
        try {
            context.startActivity(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                if (pdfFile.exists()) {
                    val deleted = pdfFile.delete()
                    if (!deleted) {
                        Log.w("GiftCard", "Failed to delete PDF file: ${pdfFile.absolutePath}")
                    }
                }
            }, 15000)

        } catch (e: Exception) {
            Toast.makeText(context, "Failed to share PDF via WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
}

class EmptyWhatsAppSender : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
    }

    override fun sendPdf(context: Context, pdfFile: File) {
    }
}