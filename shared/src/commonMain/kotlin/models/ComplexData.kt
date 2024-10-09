package models

import kotlinx.serialization.Serializable

@Serializable
data class ComplexData(
    val title: String,
    val simple: SimpleData
) {
    @Serializable
    data class SimpleData(
        val text: String,
    )
}
