package me.mushroommif

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps
import kotlin.properties.Delegates

class MultipleCodec<V>(val codecs: Collection<Codec<V>>): Codec<V> {
    init {
        require(codecs.isNotEmpty()) { "At least one codec must be provided" }
    }

    override fun <T> encode(input: V, ops: DynamicOps<T>, prefix: T): DataResult<T> {
        var lastResult by Delegates.notNull<DataResult<T>>()
        for (codec in codecs) {
            lastResult = codec.encode(input, ops, prefix)
            if (lastResult.result().isPresent) {
                break
            }
        }

        return lastResult
    }

    override fun <T> decode(ops: DynamicOps<T>, input: T): DataResult<Pair<V, T>> {
        var lastResult by Delegates.notNull<DataResult<Pair<V, T>>>()
        for (codec in codecs) {
            lastResult = codec.decode(ops, input)
            if (lastResult.result().isPresent) {
                break
            }
        }

        return lastResult
    }
}

fun <T> oneOf(vararg codecs: Codec<T>): Codec<T> {
    return MultipleCodec(codecs.toList())
}