package zustand.wrappers.middlewares

import zustand.middlewareModule
import zustand.wrappers.StateCreator

external interface DevtoolsOptions {
    var name: String?
    var enabled: Boolean?
    var anonymousActionType: String?
    var store: String?
}

fun <T> devtools(creator: StateCreator<T>, options: DevtoolsOptions? = null): StateCreator<T> {
    return if (options == null) {
        middlewareModule.devtools(creator)
    } else {
        middlewareModule.devtools(creator, options)
    }.unsafeCast<StateCreator<T>>()
}