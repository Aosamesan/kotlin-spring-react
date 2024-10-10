package parameters

import org.apache.commons.codec.net.URLCodec
import java.nio.charset.StandardCharsets

actual fun encodeSegment(value: String): String {
    val codec = URLCodec(StandardCharsets.UTF_8.name())
    return codec.encode(value)
}

actual fun decodeSegment(value: String): String {
    val codec = URLCodec(StandardCharsets.UTF_8.name())
    return codec.decode(value)
}