package com.example.lib

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...${Thread.currentThread().name}")
            delay(500L)
        }
    }
    println("are you ready?")
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!${Thread.currentThread().name}")
    job.cancel() // 取消该作业
    job.join() // 等待作业执行结束
    println("main: Now I can quit.${Thread.currentThread().name}")
}