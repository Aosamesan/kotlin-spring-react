package zustand.wrappers

import js.objects.JsoDsl
import js.objects.jso

external interface SetState<T> {
    @JsName("apply")
    operator fun invoke(self: dynamic = definedExternally, value: Array<*>)
}

fun <T> SetState<T>.stateAction(state: T) {
    this.invoke(null, arrayOf(state))
}

fun <T> SetState<T>.buildAction(block: @JsoDsl T.() -> Unit) {
    this.invoke(null, arrayOf(jso(block)))
}

fun <T> SetState<T>.transformAction(transform: (T) -> T) {
    this.invoke(null, arrayOf(transform))
}