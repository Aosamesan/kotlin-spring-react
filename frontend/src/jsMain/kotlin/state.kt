import js.objects.Object
import js.objects.Record
import js.objects.jso
import models.ComplexData
import models.GuGuDan
import mui.material.styles.Theme
import parameters.Parameters
import parameters.keys.ParameterKey
import parameters.keys.ParameterKeys
import parameters.values.ParameterValue
import zustand.wrappers.*
import zustand.wrappers.middlewares.devtools
import zustand.wrappers.middlewares.zustandLogger

external interface AppState {
    var count: Int
    var message: String
    var theme: Theme
    var gugudan: GuGuDan?
    var complexData: ComplexData?
    var parameters: Parameters

    var setParameters: (Parameters) -> Unit
    var updateParameter: (ParameterKey, ParameterValue) -> Unit
    var setGuGuDan: (GuGuDan) -> Unit
    var setComplexData: (ComplexData) -> Unit
    var increment: () -> Unit
    var decrement: () -> Unit
    var setMessage: (String) -> Unit
    var switchTheme: (Theme) -> Unit
    var reset: () -> Unit
}

val AppStateStoreContext = createStoreContext<AppState>()

val AppStateStore = createStore<AppState>(
    devtools(
        zustandLogger(
            ::createAppState
        )
    )
)

private fun createAppState(
    setState: SetState<AppState>,
    getState: GetState<AppState>,
    store: StoreApi<AppState>
): AppState = jso {
    count = 0
    message = ""
    theme = Themes.Dark
    parameters = mutableMapOf()

    increment = {
        setState.transformAction { state ->
            jso {
                count = state.count + 1
            }
        }
    }

    decrement = {
        setState.transformAction { state ->
            jso {
                count = state.count - 1
            }
        }
    }

    setMessage = { message ->
        setState.buildAction {
            this.message = message
        }
    }

    switchTheme = { theme ->
        setState.stateAction(jso {
            this.theme = theme
        })
    }

    reset = {
        setState.transformAction { state ->
            val initialState = store.getInitialState()
            jso {
                for (key in Object.keys(this)) {
                    if (key == "theme") continue
                    this.asDynamic()[key] = initialState.asDynamic()[key]
                }
            }
        }
    }

    setGuGuDan = { gugudan ->
        setState.buildAction {
            this.gugudan = gugudan
        }
    }

    setComplexData = { complexData ->
        setState.buildAction {
            this.complexData = complexData
        }
    }

    setParameters = { parameters ->
        setState.buildAction {
            this.parameters = parameters
        }
    }

    updateParameter = { key, value ->
        console.log("UpdateParameter :", key, value)
        setState.transformAction { state ->
            jso {
                parameters = HashMap(state.parameters).also {
                    it[key] = value
                }
            }
        }
    }
}