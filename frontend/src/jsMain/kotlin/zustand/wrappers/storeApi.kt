package zustand.wrappers

typealias GetState<T> = () -> T

external interface StoreApi<T> {
    var setState: SetState<T>
    var getState: GetState<T>
    var getInitialState: GetState<T>
}