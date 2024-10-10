package parameters.keys

import kotlin.reflect.KClass

sealed interface ParameterKey {
    val key: String
    val valueType: KClass<*>
}