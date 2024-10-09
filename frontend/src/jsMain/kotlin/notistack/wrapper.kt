package notistack

import react.ReactNode


fun useEnqueueSnackbar(): EnqueueSnackbar {
    return useSnackbar().enqueueSnackbar
}

fun useCloseSnackbar(): CloseSnackbar {
    return useSnackbar().closeSnackbar
}

operator fun CloseSnackbar.invoke(key: String? = null) {
    if (key != null) {
        invoke(null, key)
    } else {
        invoke()
    }
}

operator fun CloseSnackbar.invoke(key: Number) {
    invoke(null, key)
}

operator fun EnqueueSnackbar.invoke(options: OptionObjectWithStringMessage<BaseVariant>): Any {
    return invoke(null, options)
}

operator fun EnqueueSnackbar.invoke(options: OptionObjectWithReactMessage<BaseVariant>): Any {
    return invoke(null, options)
}

operator fun EnqueueSnackbar.invoke(message: String, option: OptionObject<BaseVariant>? = null): Any {
    return if (option != null) {
        invoke(null, message, option)
    } else {
        invoke<BaseVariant>(null, message)
    }
}

operator fun EnqueueSnackbar.invoke(message: ReactNode, option: OptionObject<BaseVariant>? = null): Any {
    return if (option != null) {
        invoke(null, message, option)
    } else {
        invoke<BaseVariant>(null, message)
    }
}