package com.example.firstdescendant.network

import com.example.firstdescendant.data.user.basicinfo.UserBasic
import com.example.firstdescendant.data.user.descendantinfo.UserDescendant
import com.example.firstdescendant.data.user.ouid.UserOuid
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DescendantApiService {
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("id")
    suspend fun getUserOuid(
        @Query("user_name") user_name: String = ""
    ) : UserOuid

    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("user/basic")
    suspend fun getUserInfo(
        @Query("ouid") ouid: String = ""
    ) : UserBasic

    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("user/descendant")
    suspend fun getUserDescendantInfo(
        @Query("ouid") ouid: String = ""
    ) : UserDescendant
}