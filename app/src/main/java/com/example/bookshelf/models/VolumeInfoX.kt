package com.example.bookshelf.models

data class VolumeInfoX(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val canonicalVolumeLink: String,
    val contentVersion: String,
    val description: String,
    val dimensions: Dimensions,
    val imageLinks: ImageLinksX,
    val industryIdentifiers: List<IndustryIdentifierX>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummaryX,
    val previewLink: String,
    val printType: String,
    val printedPageCount: Int,
    val publishedDate: String,
    val publisher: String,
    val readingModes: ReadingModesX,
    val subtitle: String,
    val title: String
)