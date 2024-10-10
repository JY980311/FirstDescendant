package com.example.firstdescendant.network

import com.example.firstdescendant.data.user.reactor.UserReactorImage
import com.example.firstdescendant.data.user.reactor.UserReactorName
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SupabaseApiService {

    @GET("R_reactors")
    suspend fun getUserReactorName(
        @Query("select") select: String = "reactor_name",
        @Query("main_reactor_id") main_reactor_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ):  List<UserReactorName>

    @GET("R_reactors")
    suspend fun getUserReactorImage(
        @Query("select") select: String = "image_url",
        @Query("main_reactor_id") main_reactor_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserReactorImage>
}
