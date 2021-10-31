package com.example.android.technicaltest.model

import com.google.gson.annotations.SerializedName

data class DataListResponse(
    @SerializedName("data")
    val data: List<DataEntity>
)

data class DataEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("picture")
    val picture: String
)