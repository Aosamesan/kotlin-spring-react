package parameters

import parameters.keys.ParameterKey
import parameters.values.ParameterValue

typealias Parameters = Map<ParameterKey, ParameterValue>

fun Parameters.toQueryString() : String {
    return entries.joinToString("&") { (key, value) ->
        "${key.key}=${encodeSegment(value.value)}"
    }
}