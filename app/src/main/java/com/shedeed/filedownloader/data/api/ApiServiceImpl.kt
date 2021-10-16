package com.shedeed.filedownloader.data.api

import com.shedeed.filedownloader.data.model.File
import io.reactivex.Single
import com.rx2androidnetworking.Rx2AndroidNetworking

class ApiServiceImpl : ApiService {
    override fun getFiles(): Single<List<File>> {
        return Rx2AndroidNetworking.get("https://nagwa.free.beeceptor.com/movies/")
            .build()
            .getObjectListSingle(File::class.java)
    }
}