package parameters.keys

import kotlinx.serialization.Serializable
import parameters.values.FooValues
import parameters.values.StringValue
import kotlin.reflect.KClass

@Serializable
enum class ParameterKeys(
    override val key: String,
    initValueType: KClass<*> = StringValue::class
) : ParameterKey {
    Foo("foo", FooValues::class),
    Bar("bar"),
    Baz("baz");

    override val valueType: KClass<*> = initValueType
}