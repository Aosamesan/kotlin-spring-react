@file:JsModule("notistack")
@file:JsNonModule

package notistack

import react.FC

external val SnackbarProvider: FC<SnackbarProviderProps> = definedExternally

external fun useSnackbar(): ProviderContext = definedExternally