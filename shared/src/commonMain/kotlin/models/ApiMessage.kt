package models

import enums.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class ApiMessage(
    val message: String,
    val type: MessageType
)
