package com.example.android.technicaltest.model

import com.google.gson.annotations.SerializedName

data class DataListResponse(
    @SerializedName("data")
    val data: List<DataListEntity>
)

data class DataListEntity (
    @SerializedName("title")
    val title: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("picture")
    val picture: String
)