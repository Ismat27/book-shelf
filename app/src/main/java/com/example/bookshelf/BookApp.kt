package com.example.bookshelf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.ui.screens.BookShelfUiState
import com.example.bookshelf.ui.screens.BookShelfViewModel

@Composable
fun BookApp(modifier: Modifier = Modifier) {

    val bookAppViewModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
    val imageList by bookAppViewModel.imageList.collectAsState()

    Scaffold(modifier.fillMaxSize(), topBar = { BookShelfTopAppBar() }) {
        Surface(modifier = Modifier.padding(it)) {
            when (bookAppViewModel.bookSelfUiState) {
                is BookShelfUiState.Loading -> LoadingScreen()
                is BookShelfUiState.Error -> ErrorScreen(onRetry = { bookAppViewModel.getBookImages() })
                is BookShelfUiState.Success -> BookImages(images = imageList)
            }
        }
    }
}

@Composable
fun BookImages(images: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = images, key = { image -> image }) {
            BookImage(
                imgSrc = it.replace("http", "https"),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Composable
fun BookImage(imgSrc: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imgSrc).crossfade(true).build(),
        modifier = modifier,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "An error occurred")
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfTopAppBar() {
    CenterAlignedTopAppBar(title = { Text(text = "Book Shelf") })
}