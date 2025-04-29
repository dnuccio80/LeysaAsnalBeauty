package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsEntity

data class AnnotationsDataClass(
    val id: Int = 0,
    val title: String,
    val description: String,
)

fun AnnotationsDataClass.toAnnotationsEntity() : AnnotationsEntity = AnnotationsEntity(
    id = id,
    title = title,
    description = description
)
