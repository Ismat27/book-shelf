package com.example.bookshelf.models

data class BookSearch(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)