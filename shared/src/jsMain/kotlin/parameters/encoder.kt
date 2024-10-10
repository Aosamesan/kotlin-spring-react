package parameters

external fun encodeURIComponent(uri: String): String
external fun decodeURIComponent(encodedURI: String): String

actual fun encodeSegment(value: String): String = encodeURIComponent(value)
actual fun decodeSegment(value: String): String = decodeURIComponent(value)