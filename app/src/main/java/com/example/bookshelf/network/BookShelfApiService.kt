package com.example.bookshelf.network

import com.example.bookshelf.models.BookDetail
import com.example.bookshelf.models.BookSearch
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookShelfApiService {

    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BookSearch

    @GET("volumes/{id}")
    suspend fun getBookDetails(@Path("id") bookId: String): BookDetail

}