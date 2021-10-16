package com.shedeed.filedownloader.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getFiles() = apiService.getFiles()
}