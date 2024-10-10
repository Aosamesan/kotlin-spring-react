package pages

import AppState
import AppStateStoreContext
import httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import js.objects.jso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import mui.material.*
import parameters.getParameterValue
import parameters.keys.ParameterKey
import parameters.keys.ParameterKeys
import parameters.parseQueryMap
import parameters.parseQueryStringToMap
import parameters.toQueryString
import parameters.values.FooValues
import parameters.values.ParameterValue
import react.FC
import react.createRef
import react.dom.html.ReactHTML.form
import react.router.useLocation
import react.router.useNavigate
import react.useEffectOnce
import react.useRef
import web.html.ButtonType
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.html.HTMLSelectElement
import zustand.wrappers.useStore

val EchoParamPage = FC {
    val parameters = AppStateStoreContext.useStore(AppState::parameters)
    val setParameters = AppStateStoreContext.useStore(AppState::setParameters)
    val location = useLocation()
    val navigate = useNavigate()
    val updateParameter = AppStateStoreContext.useStore(AppState::updateParameter)


    useEffectOnce {
        val queryStringMap = parseQueryStringToMap(location.search)
        val queryParameters = parseQueryMap(queryStringMap).toMutableMap()
        setParameters(queryParameters)
    }

    Typography {
        +"Echo Params"
        gutterBottom = true
    }

    form {
        onSubmit = { event ->
            event.preventDefault()
            navigate("?${parameters.toQueryString()}")
        }

        FormGroup {
            row = true

            FormControl {
                fullWidth = true

                InputLabel {
                    id = "input-foo-label"
                    +"Foo"
                }
                Select {
                    labelId = "input-foo-label"
                    value = parameters[ParameterKeys.Foo]?.value ?: FooValues.One.value
                    name = "foo"
                    onChange = { event, _ ->
                        console.log(event.target.value)
                        val newValue = getParameterValue(event.target.value, FooValues::class)
                        console.log(ParameterKeys.Foo)
                        console.log(newValue)
                        updateParameter(ParameterKeys.Foo, newValue)
                    }

                    FooValues.entries.forEach { fooEntry ->
                        MenuItem {
                            key = fooEntry.value
                            value = fooEntry.value
                            +fooEntry.name
                        }
                    }
                }
            }
        }


        Button {
            type = ButtonType.submit
            +"Submit"
        }
    }
}