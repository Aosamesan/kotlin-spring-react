import parameters.parseQueryMap
import parameters.parseQueryStringToMap
import kotlin.test.Test

class ParameterTests {
    @Test
    fun queryStringTest() {
        val query = "foo=1&bar=2&baz=3&fizz=4&buzz=5"
        val queryMap = parseQueryStringToMap(query)
        val parameterMap = parseQueryMap(queryMap)
        println(
            parameterMap
        )
    }
}