package me.mushroommif

import com.mojang.serialization.DataResult

fun <T> Result<T>.toDataResult(): DataResult<T> {
    return if (isSuccess) {
        DataResult.success(getOrNull()!!)
    } else {
        DataResult.error { exceptionOrNull()!!.message }
    }
}

fun <T> DataResult<T>.toResult(): Result<T> {
    val result = this.result()
    return if (result.isPresent) {
        Result.success(result.get())
    } else {
        Result.failure(Exception(this.error().get().message()))
    }
}