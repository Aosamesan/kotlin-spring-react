package parameters.keys

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import parameters.values.StringValue
import kotlin.reflect.KClass

@Serializable
data class StringKey(
    override val key: String,
): ParameterKey {
    @Transient
    override val valueType: KClass<*> = StringValue::class

    override fun toString(): String = key
}
