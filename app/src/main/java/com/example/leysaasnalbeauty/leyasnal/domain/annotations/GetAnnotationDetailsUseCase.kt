package com.example.leysaasnalbeauty.leyasnal.domain.annotations

import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsRepository
import javax.inject.Inject

class GetAnnotationDetailsUseCase @Inject constructor(private val annotationsRepository: AnnotationsRepository) {
    suspend operator fun invoke(annotationId:Int) = annotationsRepository.getAnnotationDetail(annotationId)
}