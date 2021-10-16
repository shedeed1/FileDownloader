package com.shedeed.filedownloader.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shedeed.filedownloader.data.api.ApiHelper
import com.shedeed.filedownloader.data.api.ApiServiceImpl
import com.shedeed.filedownloader.data.model.File
import com.shedeed.filedownloader.databinding.ActivityMainBinding
import com.shedeed.filedownloader.ui.base.ViewModelFactory
import com.shedeed.filedownloader.ui.main.adapter.FileAdapter
import com.shedeed.filedownloader.ui.main.viewmodel.FileViewModel
import com.shedeed.filedownloader.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fileViewModel: FileViewModel
    private lateinit var adapter: FileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FileAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        fileViewModel.getFiles().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
                    it.data?.let { files -> renderList(files) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
//                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
//                    progressBar.visibility = View.GONE
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(files: List<File>) {
        adapter.addData(files)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        fileViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(FileViewModel::class.java)
    }
}