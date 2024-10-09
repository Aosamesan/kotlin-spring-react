package zustand.wrappers.middlewares

import zustand.wrappers.SetState
import zustand.wrappers.StateCreator

private fun <T> groupedLog(prevState: T, nextState: T, action: dynamic) {
    js("console.group('%caction %c%s', 'color:red;font-weight:bold;', 'color:inherit;font-weight:inherit;', action.name || 'Partial Update')")
    console.log("%cprev state %o", "color:grey;font-weight:bold;", prevState)
    console.log("%caction %o", "color:blue;font-weight:bold;", action)
    console.log("%cnext state %o", "color:green;font-weight:bold;", nextState)
    js("console.groupEnd()")
}

private fun <T> spreadLog(prevState: T, nextState: T, action: dynamic) {
    console.log(
        "%caction %c%s",
        "color:red;font-weight:bold;",
        "color:inherit;font-weight:inherit;",
        action.name ?: "Partial Update"
    )
    console.log("%cprev state %o", "color:grey;font-weight:bold;", prevState)
    console.log("%caction %o", "color:blue;font-weight:bold;", action)
    console.log("%cnext state %o", "color:green;font-weight:bold;", nextState)
}

fun <T> zustandLogger(stateCreator: StateCreator<T>): StateCreator<T> {
    return { set, get, api ->
        val newSet: SetState<T> = ({ action: dynamic ->
            val prevState = get()
            js("set.apply(null, arguments)")
            val nextState = get()
            if (jsTypeOf(js("console.group")) != "undefined") {
                groupedLog(prevState, nextState, action)
            } else {
                spreadLog(prevState, nextState, action)
            }
        }).unsafeCast<SetState<T>>()

        stateCreator(newSet, get, api)
    }
}