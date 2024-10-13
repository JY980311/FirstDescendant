package com.example.firstdescendant.network

import com.example.firstdescendant.data.user.basicinfo.UserBasicData
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantData
import com.example.firstdescendant.data.user.external.UserExternalData
import com.example.firstdescendant.data.user.ouid.UserOuid
import com.example.firstdescendant.data.user.reactor.UserReactorData
import com.example.firstdescendant.data.user.weapon.UserWeaponData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DescendantApiService {

    /** Ouid 얻는 GET */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("id")
    suspend fun getUserOuid(
        @Query("user_name") user_name: String = ""
    ) : UserOuid

    /** Ouid를 통해 기본적인 유저의 정보 조회 */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("user/basic")
    suspend fun getUserInfo(
        @Query("ouid") ouid: String = ""
    ) : UserBasicData

    /** Ouid를 통해 유저가 장착 중인 계승자 정보 조회 */
    @Headers(
        "accept: application/json",
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
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
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
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
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
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
        "x-nxopen-api-key: test_5219217a65eb1d692f5dd6fdfc38d36cb03f0514af2c00d90bd3e547de61c7f0efe8d04e6d233bd35cf2fabdeb93fb0d"
    )
    @GET("user/external-component")
    suspend fun getUserExternalInfo(
        @Query("language_code") language_code: String ="ko",
        @Query("ouid") ouid: String = ""
    ) : UserExternalData
}
