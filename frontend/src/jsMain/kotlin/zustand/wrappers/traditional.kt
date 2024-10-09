package zustand.wrappers

import zustand.traditionalModule

typealias EqualityFunction<U> = (a: U, b: U) -> Boolean

fun <T> useStoreWithEqualityFn(api: StoreApi<T>): T {
    return traditionalModule.useStoreWithEqualityFn(api).unsafeCast<T>()
}

fun <T, TSlice> useStoreWithEqualityFn(api: StoreApi<T>, selector: (T) -> TSlice): TSlice {
    return traditionalModule.useStoreWithEqualityFn(api, selector).unsafeCast<TSlice>()
}

fun <T, TSlice> useStoreWithEqualityFn(api: StoreApi<T>, selector: (T) -> TSlice, equalityFn: EqualityFunction<TSlice>): TSlice {
    return traditionalModule.useStoreWithEqualityFn(api, selector, equalityFn).unsafeCast<TSlice>()
}