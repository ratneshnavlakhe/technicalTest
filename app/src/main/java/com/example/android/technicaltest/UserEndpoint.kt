package com.example.android.technicaltest

import com.example.android.technicaltest.model.User
import com.example.android.technicaltest.model.UserListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserEndpoint {

    @Headers("app-id: 617a643fd02e388d62fef08a")
    @GET("data/v1/user?limit=100")
    fun getUserList(): Single<UserListResponse>

    @Headers("app-id: 617a643fd02e388d62fef08a")
    @GET("data/v1/user/{user_id}")
    fun getUser(@Path("user_id") userId: String): Single<User>
}