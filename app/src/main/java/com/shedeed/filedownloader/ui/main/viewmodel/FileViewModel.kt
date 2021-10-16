package com.shedeed.filedownloader.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shedeed.filedownloader.data.model.File
import com.shedeed.filedownloader.data.repository.Repository
import com.shedeed.filedownloader.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FileViewModel(private val repository: Repository) : ViewModel() {

    private val files = MutableLiveData<Resource<List<File>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchFiles()
    }

    private fun fetchFiles() {
        files.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getFiles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ fileList ->
                    files.postValue(Resource.success(fileList))
                }, { throwable ->
                    files.postValue(Resource.error("An error has occurred", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getFiles(): LiveData<Resource<List<File>>> {
        return files
    }

}