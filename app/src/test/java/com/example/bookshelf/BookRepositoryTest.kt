package com.example.bookshelf

import com.example.bookshelf.fake.FakeBookShelfApiService
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.network.BookRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BookRepositoryTest {

    private val repository = BookRepository(FakeBookShelfApiService())

    @Test
    fun bookRepository_searchBook() = runTest {
        val searchResult = repository.searchBooks("")
        assertEquals(FakeDataSource.BookSearch.totalItems, searchResult.totalItems)
        assertEquals(FakeDataSource.BookSearch.kind, searchResult.kind)
    }

    @Test
    fun bookRepository_getBookDetails() = runTest {
        val bookIds = listOf("item1", "item2", "item3", "item4", "item5")
        val bookId = bookIds.random()
        val bookDetail = repository.getBookDetails(bookId = bookId)
        assertEquals(bookId, bookDetail.id)
    }

}