package com.example.bookshelf.models

data class Item(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)