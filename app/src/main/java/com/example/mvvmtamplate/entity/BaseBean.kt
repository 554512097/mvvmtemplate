package com.example.mvvmtamplate.entity

/**
 * Author: kevin
 * Date: 2023/3/31
 * Description:
 */
data class BaseBean<T>(val code: Int, val msg: String, val data: T)