package parameters

import parameters.keys.ParameterKey
import parameters.keys.ParameterKeys
import parameters.keys.StringKey
import parameters.values.FooValues
import parameters.values.ParameterValue
import parameters.values.StringValue
import kotlin.reflect.KClass

fun parseQueryStringToMap(queryString: String): Map<String, String> {
    return queryString.removePrefix("?")
        .split("&")
        .filter(String::isNotEmpty)
        .map { it.split("=") }
        .associate {
            val key = it[0]
            val value = it[1]
            key to decodeSegment(value)
        }
}

private val KnownParameterKeys: List<String> = ArrayList(ParameterKeys.entries).map(ParameterKey::key)

fun parseQueryMap(queryMap: Map<String, String>): Parameters {
    return queryMap.entries.associate { (key, value) ->
        val parameterKey = getParameterKey(key)
        val parameterValue = getParameterValue(value, parameterKey.valueType)
        parameterKey to parameterValue
    }
}

fun getParameterKey(key: String): ParameterKey {
    return getEnumParameterKey<ParameterKeys>(key) ?: StringKey(key)
}

fun getParameterValue(value: String, valueType: KClass<*>): ParameterValue {
    return when (valueType) {
        FooValues::class -> requireNotNull(getEnumParameterValue<FooValues>(value))
        else -> StringValue(value)
    }
}

inline fun <reified T> getEnumParameterKey(key: String): T? where T : ParameterKey, T : Enum<T> {
    return enumValues<T>().find { it.key == key }
}

inline fun <reified T> getEnumParameterValue(value: String): T? where T : ParameterValue, T : Enum<T> {
    return enumValues<T>().find { it.value == value }
}
