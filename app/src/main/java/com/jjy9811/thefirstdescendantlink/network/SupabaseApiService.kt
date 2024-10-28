package com.jjy9811.thefirstdescendantlink.network

import com.jjy9811.thefirstdescendantlink.BuildConfig
import com.jjy9811.thefirstdescendantlink.data.user.descendantinfo.UserDescendantInfo
import com.jjy9811.thefirstdescendantlink.data.user.external.UserExternalInfo
import com.jjy9811.thefirstdescendantlink.data.user.external.UserExternalStatName
import com.jjy9811.thefirstdescendantlink.data.user.external.UserExternalStatValue
import com.jjy9811.thefirstdescendantlink.data.user.module.UserDescendantModuleInfo
import com.jjy9811.thefirstdescendantlink.data.user.module.UserWeaponModuleInfo
import com.jjy9811.thefirstdescendantlink.data.user.module.UserModuleStatInfo
import com.jjy9811.thefirstdescendantlink.data.user.reactor.ReactorSkillCoefficient
import com.jjy9811.thefirstdescendantlink.data.user.reactor.UserReactorInfo
import com.jjy9811.thefirstdescendantlink.data.user.reactor.UserReactorSkillPower
import com.jjy9811.thefirstdescendantlink.data.user.weapon.UserWeaponInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SupabaseApiService {

    @GET("D_descendant")
    suspend fun getUserDescendantName(
        @Query("select") select: String = "descendant_name,descendant_image_url",
        @Query("main_descendant_id") main_descendant_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserDescendantInfo>
    @GET("R_reactors")
    suspend fun getUserReactorName(
        @Query("select") select: String = "reactor_name,image_url",
        @Query("main_reactor_id") main_reactor_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ):  List<UserReactorInfo>

    @GET("R_reactor_skill_power")
    suspend fun getUserReactorSkillPower(
        @Query("select") select: String = "*",
        @Query("reactor_id") reactor_id: String,
        @Query("level") level: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ):  List<UserReactorSkillPower>


    @GET("R_skill_power_coefficient")
    suspend fun getReactorSkillCoefficient(
        @Query("select") select: String = "coefficient_stat_id,coefficient_stat_value",
        @Query("skill_power_coefficient_id") skill_power_coefficient_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ):  List<ReactorSkillCoefficient>

    @GET("M_module")
    suspend fun getUserModuleWeapon(
        @Query("select") select: String = "main_module_id,module_name,module_tier,image_url",
        @Query("main_module_id") main_module_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserWeaponModuleInfo>

    @GET("M_module")
    suspend fun getUserModuleDescendant(
        @Query("select") select: String = "main_module_id,module_name,module_tier,image_url",
        @Query("main_module_id") main_module_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserDescendantModuleInfo>

    @GET("M_module_stat")
    suspend fun getUserModuleStat(
        @Query("select") select: String = "module_id,module_capacity,value", //value은 마음대로
        @Query("module_id") module_id: String,
        @Query("level") level: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserModuleStatInfo>
    @GET("W_weapon")
    suspend fun getUserWeaponNameImage(
        @Query("select") select: String = "main_weapon_id,weapon_name,image_url",
        @Query("main_weapon_id") main_weapon_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserWeaponInfo>

    @GET("E_external_component")
    suspend fun getUserExternalName(
        @Query("select") select: String = "external_component_name,image_url",
        @Query("main_external_component_id") main_external_component_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserExternalInfo>

    @GET("E_base_stat")
    suspend fun getUserExterStatValue(
        @Query("select") select: String = "stat_id,stat_value",
        @Query("level") level: String,
        @Query("external_component_id") external_component_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserExternalStatValue>

    @GET("S_stat")
    suspend fun getStatName(
        @Query("select") select: String = "stat_name",
        @Query("stat_id") stat_id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY
    ): List<UserExternalStatName>
}
