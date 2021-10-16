package com.shedeed.filedownloader.data.repository

import com.shedeed.filedownloader.data.api.ApiHelper
import com.shedeed.filedownloader.data.model.File
import io.reactivex.Single

class Repository(private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<File>> {
        return apiHelper.getFiles()
    }
}