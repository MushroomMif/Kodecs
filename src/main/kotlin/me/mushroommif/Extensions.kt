package me.mushroommif

import com.mojang.serialization.Codec

val Int.Companion.CODEC: Codec<Int>
    get() = Codec.INT

val String.Companion.CODEC: Codec<String>
    get() = Codec.STRING

val Boolean.Companion.CODEC: Codec<Boolean>
    get() = Codec.BOOL

val Float.Companion.CODEC: Codec<Float>
    get() = Codec.FLOAT

val Short.Companion.CODEC: Codec<Short>
    get() = Codec.SHORT

val Long.Companion.CODEC: Codec<Long>
    get() = Codec.LONG

val Double.Companion.CODEC: Codec<Double>
    get() = Codec.DOUBLE

val Byte.Companion.CODEC: Codec<Byte>
    get() = Codec.BYTE

fun <T> Codec<T>.require(validator: (T) -> Result<T>): Codec<T> {
    return this.catchingMap(validator, validator)
}

fun Codec<Int>.requireRange(min: Int, max: Int): Codec<Int> {
    return this.require {
        if (it < min || it > max) {
            Result.failure(IllegalStateException("Value must be between $min and $max"))
        } else {
            Result.success(it)
        }
    }
}

fun Codec<Int>.requireRange(range: IntRange): Codec<Int> {
    return this.requireRange(range.first, range.last)
}

fun Codec<Float>.requireRange(min: Float, max: Float): Codec<Float> {
    return this.require {
        if (it < min || it > max) {
            Result.failure(IllegalStateException("Value must be between $min and $max"))
        } else {
            Result.success(it)
        }
    }
}

fun <T, S> Codec<T>.map(to: (T) -> S, from: (S) -> T): Codec<S> {
    return this.xmap(to, from)
}

fun <T, S> Codec<T>.inputCatchingMap(to: (T) -> Result<S>, from: (S) -> T): Codec<S> {
    return this.comapFlatMap({ to(it).toDataResult() }, from)
}

fun <T, S> Codec<T>.outputCatchingMap(to: (T) -> S, from: (S) -> Result<T>): Codec<S> {
    return this.flatComapMap(to, { from(it).toDataResult() })
}

fun <T, S> Codec<T>.catchingMap(to: (T) -> Result<S>, from: (S) -> Result<T>): Codec<S> {
    return this.flatXmap({ to(it).toDataResult() }, { from(it).toDataResult() })
}