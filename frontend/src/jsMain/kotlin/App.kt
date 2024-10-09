import notistack.SnackbarProvider
import react.FC
import react.router.dom.RouterProvider
import zustand.wrappers.useStore

val App = FC {
    AppStateStoreContext.Provider {
        value = AppStateStore
        ThemedRouter()
    }
}

private val ThemedRouter = FC {
    ThemeContextProvider {
        theme = AppStateStoreContext.useStore(AppState::theme)

        SnackbarProvider {
            maxSnack = 3
            onClose = { _, reason, key ->
                console.log("Close By Key: ", key, "by", reason)
            }

            RouterProvider {
                router = appRouter
            }
        }
    }
}