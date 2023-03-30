package com.example.mvvmtamplate.net

import com.bt.apptemplate.net.RetrofitService

/**
 * Author: kevin
 * Date: 2023/3/31
 * Description:
 */
object ApiService {
    val userApi: UserApi = RetrofitService.api(UserApi::class.java)
}