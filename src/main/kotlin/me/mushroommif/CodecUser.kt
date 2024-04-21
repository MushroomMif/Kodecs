package me.mushroommif

import com.mojang.serialization.Codec
import com.mojang.serialization.DynamicOps

open class CodecUser<T>(val ops: DynamicOps<T>) {
    fun <V> encode(value: V, codec: Codec<V>): Result<T> {
        return codec.encodeStart(ops, value).toResult()
    }

    fun <V> decode(value: T, codec: Codec<V>): Result<V> {
        val result = codec.decode(ops, value)
        val pairResult = result.result()
        return if (pairResult.isPresent) {
            Result.success(pairResult.get().first)
        } else {
            Result.failure(Exception(result.error().get().message()))
        }
    }

    fun <V> encodeOrNull(value: V, codec: Codec<V>): T? {
        val result = codec.encodeStart(ops, value).result()
        return if (result.isPresent) result.get() else null
    }

    fun <V> decodeOrNull(value: T, codec: Codec<V>): V?  {
        val result = codec.decode(ops, value).result()
        return if (result.isPresent) result.get().first else null
    }
}

open class UnsafeCodecUser<T>(
    ops: DynamicOps<T>,
    private val errorCreator: (String) -> Throwable = ::error,
    private val allowPartial: Boolean = false
): CodecUser<T>(ops) {
    fun <V> encodeUnsafe(value: V, codec: Codec<V>): T {
        return if (allowPartial) {
            codec.encodeStart(ops, value).getPartialOrThrow(errorCreator)
        } else {
            codec.encodeStart(ops, value).getOrThrow(errorCreator)
        }
    }

    fun <V> decodeUnsafe(value: T, codec: Codec<V>): V {
        return if (allowPartial) {
            codec.decode(ops, value).getPartialOrThrow(errorCreator).first
        } else {
            codec.decode(ops, value).getOrThrow(errorCreator).first
        }
    }
}

fun <V, T> Codec<V>.encodeUnsafe(value: V, user: UnsafeCodecUser<T>): T {
    return user.encodeUnsafe(value, this)
}

fun <V, T> Codec<V>.decodeUnsafe(value: T, user: UnsafeCodecUser<T>): V {
    return user.decodeUnsafe(value, this)
}

fun <V, T> Codec<V>.encode(value: V, user: CodecUser<T>): Result<T> {
    return user.encode(value, this)
}

fun <V, T> Codec<V>.decode(value: T, user: CodecUser<T>): Result<V> {
    return user.decode(value, this)
}

fun <V, T> Codec<V>.encodeOrNull(value: V, user: CodecUser<T>): T? {
    return user.encodeOrNull(value, this)
}

fun <V, T> Codec<V>.decodeOrNull(value: T, user: CodecUser<T>): V? {
    return user.decodeOrNull(value, this)
}