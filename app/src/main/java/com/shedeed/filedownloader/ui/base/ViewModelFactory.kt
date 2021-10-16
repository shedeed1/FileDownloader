package com.shedeed.filedownloader.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shedeed.filedownloader.data.api.ApiHelper
import com.shedeed.filedownloader.data.repository.Repository
import com.shedeed.filedownloader.ui.main.viewmodel.FileViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FileViewModel::class.java)) {
            return FileViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}