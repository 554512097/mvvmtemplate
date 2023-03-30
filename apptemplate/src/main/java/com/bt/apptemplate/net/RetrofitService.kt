package com.bt.apptemplate.net

import android.util.Log
import com.bt.apptemplate.TAG
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Author: kevin
 * Date: 2023/3/31
 * Description:
 */
object RetrofitService {
    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        val logging = HttpLoggingInterceptor { message -> Log.d(TAG, message) }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        addInterceptor(logging)
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.11.5.13:8081/")
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val apis = mutableMapOf<Class<*>, Any>()

    fun <T> api(api: Class<*>): T {
        if (apis[api] == null) {
            apis[api] = retrofit.create(api)
        }
        return apis[api] as T
    }
}