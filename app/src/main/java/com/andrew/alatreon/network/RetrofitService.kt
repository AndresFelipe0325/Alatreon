package com.andrew.alatreon.network

import com.andrew.alatreon.BuildConfig
import com.andrew.alatreon.util.baseUrl
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    companion object {
        /** Applying an interceptor in order to catch the logs of network operations to find out what is going on*/
        private val loggerInterceptor = HttpLoggingInterceptor().apply {
            /** Depending of what are you looking for there are 3 levels: BASIC, BODY AND HEADERS*/
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                // Request customization: add request headers
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
            this.addInterceptor(loggerInterceptor)
                /** Adding timeouts to the connection with server */
                .connectTimeout(30, TimeUnit.SECONDS) // Time for establishing connection with server.
                .readTimeout(25, TimeUnit.SECONDS) // Maximum time gap between arrivals of two data packages while waiting for server response
                .writeTimeout(25, TimeUnit.SECONDS) //Maximum time gap between 2 data packages when sending them to the server
        }.build()

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}