package com.example.firstdescendant.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient2 {
    companion object { // 객체 생성 없이 사용할 수 있도록 함

        private const val descendantApi = "https://open.api.nexon.com/static/tfd/"

        private var descendantApiService: DescendantApiService? = null

        fun TestgetDecendantApi(): DescendantApiService {
            if (descendantApiService == null) {

                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                descendantApiService = Retrofit.Builder()
                    .baseUrl(descendantApi)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
                    .create(DescendantApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return descendantApiService!! // apiService가 null이 아닌 경우 반환
        }
    }
}