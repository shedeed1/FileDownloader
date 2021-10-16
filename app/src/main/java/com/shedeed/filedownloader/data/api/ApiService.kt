package com.shedeed.filedownloader.data.api

import com.shedeed.filedownloader.data.model.File
import io.reactivex.Single

interface ApiService {
    fun getFiles(): Single<List<File>>
}