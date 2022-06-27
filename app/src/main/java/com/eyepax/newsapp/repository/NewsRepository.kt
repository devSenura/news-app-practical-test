package com.eyepax.newsapp.repository

import com.eyepax.newsapp.api.APIClient

class NewsRepository() {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        APIClient.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        APIClient.api.searchForNews(searchQuery, pageNumber)

}