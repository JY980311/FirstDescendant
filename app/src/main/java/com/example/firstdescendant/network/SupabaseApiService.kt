package com.example.firstdescendant.network

import com.example.firstdescendant.data.user.descendantinfo.UserDescendantName
import com.example.firstdescendant.data.user.external.UserExternalName
import com.example.firstdescendant.data.user.module.UserModuleInfo
import com.example.firstdescendant.data.user.reactor.UserReactorImage
import com.example.firstdescendant.data.user.reactor.UserReactorName
import com.example.firstdescendant.data.user.weapon.UserWeaponInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SupabaseApiService {

    @GET("D_descendant")
    suspend fun getUserDescendantName(
        @Query("select") select: String = "descendant_name",
        @Query("main_descendant_id") main_descendant_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserDescendantName>
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

    @GET("M_module")
    suspend fun getUserModuleName(
        @Query("select") select: String = "main_module_id,module_name,module_tier,image_url",
        @Query("main_module_id") main_module_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserModuleInfo>

    @GET("W_weapon")
    suspend fun getUserWeaponNameImage(
        @Query("select") select: String = "main_weapon_id,weapon_name,image_url",
        @Query("main_weapon_id") main_weapon_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserWeaponInfo>

    @GET("E_external_component")
    suspend fun getUserExternalName(
        @Query("select") select: String = "external_component_name,image_url",
        @Query("main_external_component_id") main_external_component_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserExternalName>
}
