package pages

import AppState
import AppStateStoreContext
import enums.MessageType
import httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import js.objects.jso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.ApiMessage
import mui.material.Button
import mui.material.FormGroup
import mui.material.TextField
import mui.material.Typography
import notistack.BaseVariants
import notistack.invoke
import notistack.useEnqueueSnackbar
import react.FC
import react.dom.onChange
import react.useState
import web.html.HTMLInputElement
import zustand.wrappers.useStore

val EchoPage = FC {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val (inputMessage, setInputMessage) = useState("")
    val message = AppStateStoreContext.useStore(AppState::message)
    val setMessage = AppStateStoreContext.useStore(AppState::setMessage)
    val enqueueSnackbar = useEnqueueSnackbar()

    Typography {
        +"Current Message : $message"
    }
    FormGroup {
        TextField {
            value = inputMessage
            inputProps = jso {
                onChange = { value -> setInputMessage((value.currentTarget as? HTMLInputElement)?.value ?: "") }
            }
        }
        Button {
            onClick = {
                coroutineScope.launch {
                    val response = httpClient.get("/api/echo?message=$inputMessage")
                    val apiMessage = response.body<ApiMessage>()
                    enqueueSnackbar(apiMessage.message, jso {
                        variant = when (apiMessage.type) {
                            MessageType.INFO -> BaseVariants.INFO
                            MessageType.ERROR -> BaseVariants.ERROR
                            MessageType.WARNING -> BaseVariants.WARNING
                            else -> "default"
                        }
                    })
                    setMessage(apiMessage.message)
                }
            }
            +"Send"
        }
    }
}