package com.example.thefirstdescendantlink.network

import com.example.thefirstdescendantlink.data.user.basicinfo.UserBasicData
import com.example.thefirstdescendantlink.data.user.descendantinfo.UserDescendantData
import com.example.thefirstdescendantlink.data.user.external.UserExternalData
import com.example.thefirstdescendantlink.data.user.ouid.UserOuid
import com.example.thefirstdescendantlink.data.user.reactor.UserReactorData
import com.example.thefirstdescendantlink.data.user.weapon.UserWeaponData
import com.example.thefirstdescendantlink.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DescendantApiService {

    /** Ouid 얻는 GET */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("id")
    suspend fun getUserOuid(
        @Query("user_name") user_name: String = ""
    ) : UserOuid

    /** Ouid를 통해 기본적인 유저의 정보 조회 */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("user/basic")
    suspend fun getUserInfo(
        @Query("ouid") ouid: String = ""
    ) : UserBasicData

    /** Ouid를 통해 유저가 장착 중인 계승자 정보 조회 */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("user/descendant")
    suspend fun getUserDescendantInfo(
        @Query("ouid") ouid: String = ""
    ) : UserDescendantData

    /**
     * Ouid를 통해 장착 중인 무기 정보 조회
     * language_code: 언어 코드 (기본값: ko)
     * */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("user/weapon")
    suspend fun getUserWeaponInfo(
        @Query("language_code") language_code: String ="ko",
        @Query("ouid") ouid: String = ""
    ) : UserWeaponData

    /**
     * Ouid를 통해 장착 중인 반응로 정보 조회
     * language_code: 언어 코드 (기본값: ko)
     * */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("user/reactor")
    suspend fun getUserReactorInfo(
        @Query("language_code") language_code: String ="ko",
        @Query("ouid") ouid: String = ""
    ) : UserReactorData

    /**
     * Ouid를 통해 장착 중인 외장부품 정보 조회
     * language_code: 언어 코드 (기본값: ko)
     * */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: ${BuildConfig.NEXON_API_KEY}"
    )
    @GET("user/external-component")
    suspend fun getUserExternalInfo(
        @Query("language_code") language_code: String ="ko",
        @Query("ouid") ouid: String = ""
    ) : UserExternalData
}
