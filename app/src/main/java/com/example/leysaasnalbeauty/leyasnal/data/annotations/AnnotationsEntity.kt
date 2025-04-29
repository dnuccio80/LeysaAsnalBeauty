package com.example.leysaasnalbeauty.leyasnal.data.annotations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AnnotationsDataClass

@Entity
data class AnnotationsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
)

fun AnnotationsEntity.toAnnotationsDataClass(): AnnotationsDataClass = AnnotationsDataClass(
    id = id,
    title = title,
    description = description
)
