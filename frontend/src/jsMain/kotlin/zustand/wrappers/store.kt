package zustand.wrappers

import zustand.createStoreModule
import zustand.useStoreModule

typealias StateCreator<T> = (setState: SetState<T>, getState: GetState<T>, store: StoreApi<T>) -> T

fun <T> createStore(initializer: StateCreator<T>): StoreApi<T> {
    return createStoreModule.createStore(initializer).unsafeCast<StoreApi<T>>()
}

fun <T> createStore(): ((initializer: StateCreator<T>) -> StoreApi<T>) {
    return { initializer ->
        createStore(initializer)
    }
}

fun <T> useStore(store: StoreApi<T>): T {
    return useStoreModule.useStore(store).unsafeCast<T>()
}

fun <T, U> useStore(store: StoreApi<T>, selector: (T) -> U): U {
    return useStoreModule.useStore(store, selector).unsafeCast<U>()
}