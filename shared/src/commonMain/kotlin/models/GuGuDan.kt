package models

import kotlinx.serialization.Serializable

@Serializable
data class GuGuDan(
    val title: String,
    val table: Map<Int, Map<Int, Int>>
)