package com.example.bookshelf.fake

import com.example.bookshelf.models.BookDetail
import com.example.bookshelf.models.BookSearch
import com.example.bookshelf.network.IBookRepository

class FakeBookRepository: IBookRepository {
    override suspend fun searchBooks(query: String): BookSearch {
        return FakeDataSource.BookSearch
    }

    override suspend fun getBookDetails(bookId: String): BookDetail {
        val book = FakeDataSource.books.find { item -> item.id == bookId }
        return BookDetail(
            etag = "etag $bookId",
            id = bookId,
            kind = "book",
            volumeInfo = book!!.volumeInfo,
            selfLink = book.selfLink
        )
    }
}