package com.example.android.technicaltest.model

import com.google.gson.annotations.SerializedName

data class UserListResponse(
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

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("phone")
    val phone: String
)