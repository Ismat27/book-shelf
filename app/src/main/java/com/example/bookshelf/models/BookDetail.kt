package com.example.bookshelf.models

data class BookDetail(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfoX,
    val selfLink: String,
    val volumeInfo: VolumeInfoX
)