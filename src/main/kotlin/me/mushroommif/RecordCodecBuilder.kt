package me.mushroommif

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import java.util.*

infix fun <T> Codec<T>.bindTo(fieldName: String): MapCodec<T> {
    return this.fieldOf(fieldName)
}

@Suppress("UNCHECKED_CAST")
infix fun <T> Codec<T>.bindToOptional(fieldName: String): MapCodec<T?> {
    return this.optionalFieldOf(fieldName).xmap(
        { optional -> if (optional.isPresent) optional.get() else null },
        { value -> Optional.ofNullable(value) as Optional<T> }
    )
}

infix fun <A, O> MapCodec<A>.forGet(getter: (O) -> A): RecordCodecBuilder<O, A> {
    return this.forGetter(getter)
}

fun <T, P1> createRecordCodec(p1: RecordCodecBuilder<T, P1>, creator: (P1) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1).apply(it, creator)
    }
}


fun <T, P1, P2> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, creator: (P1, P2) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2).apply(it, creator)
    }
}


fun <T, P1, P2, P3> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, creator: (P1, P2, P3) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, creator: (P1, P2, P3, P4) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, creator: (P1, P2, P3, P4, P5) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, creator: (P1, P2, P3, P4, P5, P6) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, creator: (P1, P2, P3, P4, P5, P6, P7) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, creator: (P1, P2, P3, P4, P5, P6, P7, P8) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, p12: RecordCodecBuilder<T, P12>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, p12: RecordCodecBuilder<T, P12>, p13: RecordCodecBuilder<T, P13>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, p12: RecordCodecBuilder<T, P12>, p13: RecordCodecBuilder<T, P13>, p14: RecordCodecBuilder<T, P14>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, p12: RecordCodecBuilder<T, P12>, p13: RecordCodecBuilder<T, P13>, p14: RecordCodecBuilder<T, P14>, p15: RecordCodecBuilder<T, P15>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15).apply(it, creator)
    }
}


fun <T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> createRecordCodec(p1: RecordCodecBuilder<T, P1>, p2: RecordCodecBuilder<T, P2>, p3: RecordCodecBuilder<T, P3>, p4: RecordCodecBuilder<T, P4>, p5: RecordCodecBuilder<T, P5>, p6: RecordCodecBuilder<T, P6>, p7: RecordCodecBuilder<T, P7>, p8: RecordCodecBuilder<T, P8>, p9: RecordCodecBuilder<T, P9>, p10: RecordCodecBuilder<T, P10>, p11: RecordCodecBuilder<T, P11>, p12: RecordCodecBuilder<T, P12>, p13: RecordCodecBuilder<T, P13>, p14: RecordCodecBuilder<T, P14>, p15: RecordCodecBuilder<T, P15>, p16: RecordCodecBuilder<T, P16>, creator: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) -> T): Codec<T> {
    return RecordCodecBuilder.create {
        it.group(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16).apply(it, creator)
    }
}