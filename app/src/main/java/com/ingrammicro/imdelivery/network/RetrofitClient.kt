package com.ingrammicro.imdelivery.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://apps.imonline.in/FMSWebAPI/api/"
private var retrofit: Retrofit? = null
private var okHttpClient:OkHttpClient= OkHttpClient()
class RetrofitClient{


        fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY

    }


    private fun headersInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "9908334197-Ingram")
                .build()
        )
    }
    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
        this.addInterceptor(headersInterceptor())
        this.connectTimeout(1,TimeUnit.MINUTES)
        this.writeTimeout(1,TimeUnit.MINUTES)
        this.readTimeout(1,TimeUnit.MINUTES)
    }.build()
}