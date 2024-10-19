package com.example.firstdescendant.network

import com.example.firstdescendant.data.user.descendantinfo.UserDescendantName
import com.example.firstdescendant.data.user.external.UserExternalName
import com.example.firstdescendant.data.user.module.UserDescendantModuleInfo
import com.example.firstdescendant.data.user.module.UserWeaponModuleInfo
import com.example.firstdescendant.data.user.module.UserModuleStatInfo
import com.example.firstdescendant.data.user.reactor.ReactorSkillCoefficient
import com.example.firstdescendant.data.user.reactor.UserReactorInfo
import com.example.firstdescendant.data.user.reactor.UserReactorSkillPower
import com.example.firstdescendant.data.user.weapon.UserWeaponInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SupabaseApiService {

    @GET("D_descendant")
    suspend fun getUserDescendantName(
        @Query("select") select: String = "descendant_name,descendant_image_url",
        @Query("main_descendant_id") main_descendant_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserDescendantName>
    @GET("R_reactors")
    suspend fun getUserReactorName(
        @Query("select") select: String = "reactor_name,image_url",
        @Query("main_reactor_id") main_reactor_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ):  List<UserReactorInfo>

    @GET("R_reactor_skill_power")
    suspend fun getUserReactorSkillPower(
        @Query("select") select: String = "*",
        @Query("reactor_id") reactor_id: String,
        @Query("level") level: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ):  List<UserReactorSkillPower>


    @GET("R_skill_power_coefficient")
    suspend fun getReactorSkillCoefficient(
        @Query("select") select: String = "coefficient_stat_id,coefficient_stat_value",
        @Query("skill_power_coefficient_id") skill_power_coefficient_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ):  List<ReactorSkillCoefficient>

    @GET("M_module")
    suspend fun getUserModuleWeapon(
        @Query("select") select: String = "main_module_id,module_name,module_tier,image_url",
        @Query("main_module_id") main_module_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserWeaponModuleInfo>

    @GET("M_module")
    suspend fun getUserModuleDescendant(
        @Query("select") select: String = "main_module_id,module_name,module_tier,image_url",
        @Query("main_module_id") main_module_id: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserDescendantModuleInfo>

    @GET("M_module_stat")
    suspend fun getUserModuleStat(
        @Query("select") select: String = "module_id,module_capacity,value", //value은 마음대로
        @Query("module_id") module_id: String,
        @Query("level") level: String,
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3Y3J0cGlvbndla2duYnByaGNyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY4MDc4NDksImV4cCI6MjA0MjM4Mzg0OX0.HCwdgujxTeGzUpvQK0czV1dNyM70Z7weqGWDz6U1WgY"
    ): List<UserModuleStatInfo>
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
