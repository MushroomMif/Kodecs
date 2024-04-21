import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.mushroommif.CODEC
import me.mushroommif.CodecUser
import me.mushroommif.bindTo
import me.mushroommif.createRecordCodec
import me.mushroommif.forGet
import me.mushroommif.inputCatchingMap
import me.mushroommif.oneOf
import me.mushroommif.requireRange
import me.mushroommif.toDataResult
import me.mushroommif.toResult
import kotlin.test.Test
import kotlin.test.assertEquals

object Test {
    data class Person(
        val name: String,
        val age: Int,
        val friendName: String = "Simon"
    ) {
        companion object {
            val CODEC: Codec<Person> = createRecordCodec(
                String.CODEC bindTo "name" forGet Person::name,
                Int.CODEC bindTo "age" forGet Person::age,
                String.CODEC.optionalFieldOf("friend_name", "Simon") forGet Person::friendName,
                ::Person
            )
        }
    }

    val defaultPerson = Person("Max", 34)
    val defaultPersonJson = JsonObject().apply {
        addProperty("name", "Max")
        addProperty("age", 34)
        addProperty("friend_name", "Simon")
    }

    @Test
    fun `Test createRecordCodec identity to default builder`() {
        val defaultCodec: Codec<Person> = RecordCodecBuilder.create {
            it.group(
                Codec.STRING.fieldOf("name").forGetter(Person::name),
                Codec.INT.fieldOf("age").forGetter(Person::age),
                Codec.STRING.optionalFieldOf("friend_name", "Simon").forGetter(Person::friendName)
            ).apply(it, ::Person)
        }

        assertEquals(
            defaultCodec.encodeStart(JsonOps.INSTANCE, defaultPerson),
            Person.CODEC.encodeStart(JsonOps.INSTANCE, defaultPerson)
        )

        assertEquals(
            defaultCodec.encodeStart(JsonOps.INSTANCE, Person("Max", 34, "Jack")),
            Person.CODEC.encodeStart(JsonOps.INSTANCE, Person("Max", 34, "Jack"))
        )
    }
    
    @Test
    fun `Test CodecUser identity to default decode and encode`() {
        val user = CodecUser(JsonOps.INSTANCE)

        assertEquals<JsonElement>(
            Person.CODEC.encodeStart(JsonOps.INSTANCE, defaultPerson).getOrThrow(),
            user.encode(defaultPerson, Person.CODEC).getOrThrow()
        )

        assertEquals<Person>(
            Person.CODEC.decode(JsonOps.INSTANCE, defaultPersonJson).toResult().getOrThrow().first,
            user.decode(defaultPersonJson, Person.CODEC).getOrThrow()
        )
    }

    @Test
    fun `Test result conversion`() {
        val successResult = Result.success(Unit)
        val successDataResult = DataResult.success(Unit)
        assertEquals(successDataResult, successResult.toDataResult())
        assertEquals(successResult, successDataResult.toResult())

        val failureResult = Result.failure<Unit>(Exception("Failure"))
        val failureDataResult = DataResult.error<Unit> { "Failure" }
        val convertedResult = failureResult.toDataResult()
        val convertedDataResult = failureDataResult.toResult()

        assert(convertedResult.isError)
        assertEquals("Failure", convertedResult.error().get().message())
        assert(convertedDataResult.isFailure)
        assertEquals("Failure", convertedDataResult.exceptionOrNull()!!.message)
    }

    @Test
    fun `Test range extensions`() {
        val user = CodecUser(JsonOps.INSTANCE)
        val rangeCodec = Int.CODEC.requireRange(1, 10)
        val rangeCodec2 = Int.CODEC.requireRange(1..10)
        val floatRangeCodec = Float.CODEC.requireRange(0.3f, 1.2f)

        assert(user.decode(JsonPrimitive(5), rangeCodec).isSuccess)
        assert(user.decode(JsonPrimitive(5), rangeCodec2).isSuccess)

        assert(user.decode(JsonPrimitive(0), rangeCodec).isFailure)
        assert(user.decode(JsonPrimitive(0), rangeCodec2).isFailure)

        assert(user.decode(JsonPrimitive(15), rangeCodec).isFailure)
        assert(user.decode(JsonPrimitive(15), rangeCodec2).isFailure)

        assert(user.decode(JsonPrimitive(0.3f), floatRangeCodec).isSuccess)
        assert(user.decode(JsonPrimitive(1.4f), floatRangeCodec).isFailure)
    }

    @Test
    fun `Test MultipleCodec`() {
        var isFirstWorked = false
        var isSecondWorked = false

        val firstCodec = Int.CODEC.inputCatchingMap({
            isFirstWorked = true
            if (it > 0) Result.success(it) else Result.failure(Exception("Number is too low"))
        }, { it })
        val secondCodec = Int.CODEC.inputCatchingMap({
            isSecondWorked = true
            if (it <= 0) Result.success(it) else Result.failure(Exception("Number is too high"))
        }, { it })
        val bothCodec = oneOf(firstCodec, secondCodec)

        val user = CodecUser(JsonOps.INSTANCE)

        user.decode(JsonPrimitive(1),  bothCodec)
        assert(isFirstWorked)
        assert(!isSecondWorked)

        user.decode(JsonPrimitive(-1),  bothCodec)
        assert(isSecondWorked)
    }
}