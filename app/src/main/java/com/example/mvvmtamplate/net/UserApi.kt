package com.example.mvvmtamplate.net

import com.example.mvvmtamplate.entity.BaseBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: kevin
 * Date: 2023/3/31
 * Description:
 */
interface UserApi {
    @GET("user/info")
    fun userInfo(@Query("id") userId: String): Flow<BaseBean<Any>>

    @GET("user/list")
    fun userList(): Flow<BaseBean<Any>>
}