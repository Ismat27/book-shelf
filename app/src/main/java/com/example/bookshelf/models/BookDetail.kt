package com.example.bookshelf.models

data class BookDetail(
    val etag: String,
    val id: String,
    val kind: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)