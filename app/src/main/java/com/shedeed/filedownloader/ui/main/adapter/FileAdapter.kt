package com.shedeed.filedownloader.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shedeed.filedownloader.data.model.File
import com.shedeed.filedownloader.databinding.FileItemBinding

class FileAdapter(
    private val files: ArrayList<File>
) : RecyclerView.Adapter<FileAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: FileItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(file: File) {
            itemBinding.fileName.text = file.name
            itemBinding.fileUrl.text = file.url

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = FileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = files.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(files[position])

    fun addData(list: List<File>) {
        files.addAll(list)
    }



}