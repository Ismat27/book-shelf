package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.models.Item
import com.example.bookshelf.network.IBookRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "BookShelfViewModel"


sealed interface BookShelfUiState {
    data object Success : BookShelfUiState
    data object Error : BookShelfUiState
    data object Loading : BookShelfUiState
}

class BookShelfViewModel(private val bookRepository: IBookRepository) : ViewModel() {

    private val _imageList = MutableStateFlow(emptyList<String>())
    val imageList: StateFlow<List<String>> = _imageList.asStateFlow()

    var bookSelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Error)
        private set

    init {
        getBookImages()
    }

    private fun updateImageList(imgUrl: String) {
        _imageList.value += imgUrl
    }

    private suspend fun fetchImages(books: List<Item>) {
        viewModelScope.launch {
            for (item in books) {
                val bookDetail = async { bookRepository.getBookDetails(item.id) }
                updateImageList(bookDetail.await().volumeInfo.imageLinks.thumbnail)
            }
        }
    }

    fun getBookImages(searchTerm: String = "jazz history") {
        bookSelfUiState = BookShelfUiState.Loading
        viewModelScope.launch {
            bookSelfUiState = try {
                val searchResult = bookRepository.searchBooks(searchTerm).items
                fetchImages(searchResult)
                BookShelfUiState.Success
            } catch (e: IOException) {
                Log.e(TAG, e.message ?: "error in getBookImages")
                BookShelfUiState.Error
            } catch (e: HttpException) {
                Log.e(TAG, e.message ?: "HttpException in getBookImages")
                BookShelfUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookRepository = application.container.bookRepository
                BookShelfViewModel(bookRepository = bookRepository)
            }
        }
    }
}