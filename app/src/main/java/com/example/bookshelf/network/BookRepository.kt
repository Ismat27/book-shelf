package com.example.bookshelf.network

import com.example.bookshelf.models.BookDetail
import com.example.bookshelf.models.BookSearch

interface IBookRepository {
    suspend fun searchBooks(query: String): BookSearch
    suspend fun getBookDetails(bookId: String): BookDetail
}

class BookRepository (private val bookShelfApiService: BookShelfApiService): IBookRepository {
    override suspend fun searchBooks(query: String): BookSearch {
        return bookShelfApiService.searchBooks(query)
    }

    override suspend fun getBookDetails(bookId: String): BookDetail {
        return bookShelfApiService.getBookDetails(bookId)
    }

}