package pages

import AppState
import AppStateStoreContext
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import zustand.wrappers.useStore

val HomePage = FC {
    val count = AppStateStoreContext.useStore(AppState::count)

    Typography {
        variant = TypographyVariant.h2

        +"Current Count : $count"
    }
}