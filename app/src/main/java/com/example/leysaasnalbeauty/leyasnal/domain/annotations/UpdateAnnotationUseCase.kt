package com.example.leysaasnalbeauty.leyasnal.domain.annotations

import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AnnotationsDataClass
import javax.inject.Inject

class UpdateAnnotationUseCase @Inject constructor(private val annotationsRepository: AnnotationsRepository) {
    suspend operator fun invoke(annotation: AnnotationsDataClass) = annotationsRepository.updateAnnotation(annotation)
}