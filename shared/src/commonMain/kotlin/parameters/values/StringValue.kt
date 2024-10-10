package parameters.values

import kotlinx.serialization.Serializable

@Serializable
data class StringValue(
    override val value: String
): ParameterValue
