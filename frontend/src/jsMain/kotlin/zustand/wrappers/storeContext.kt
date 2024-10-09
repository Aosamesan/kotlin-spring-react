package zustand.wrappers

import react.RequiredContext
import react.createRequiredContext
import react.useRequiredContext

typealias StoreContext<T> = RequiredContext<StoreApi<T>>

fun <T> createStoreContext() = createRequiredContext<StoreApi<T>>()

fun <T> StoreContext<T>.useStore() = useStore(useRequiredContext(this))

fun <T, U> StoreContext<T>.useStore(selector: (T) -> U) = useStore(react.useRequiredContext(this), selector)