package com.example.bookshelf

import com.example.bookshelf.fake.FakeBookRepository
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.screens.BookShelfUiState
import com.example.bookshelf.ui.screens.BookShelfViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BookShelfViewModelTest {

    @JvmField
    @Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookShelfViewModel_getImages_uiStateUpdateSuccess() = runTest {
        val viewModel = BookShelfViewModel(FakeBookRepository())
        assertEquals(BookShelfUiState.Success, viewModel.bookSelfUiState)
    }

    @Test
    fun bookShelfViewModel_getImages_imagesFetchedSuccessfully() = runTest {
        val viewModel = BookShelfViewModel(FakeBookRepository())
        val expectedThumbnails =
            FakeDataSource.books.map { book -> book.volumeInfo.imageLinks.thumbnail }
        assertEquals(expectedThumbnails.size, viewModel.imageList.value.size)
        for (thumbnail in viewModel.imageList.value) {
            assertTrue(thumbnail in expectedThumbnails )
        }
    }

}