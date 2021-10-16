package com.shedeed.filedownloader.data.model

import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("name")
    val name: String = ""
)