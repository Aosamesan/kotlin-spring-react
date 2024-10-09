package pages

import AppState
import AppStateStoreContext
import mui.material.Button
import mui.material.ButtonGroup
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import zustand.wrappers.useStore

val CounterPage = FC {
    val count = AppStateStoreContext.useStore(AppState::count)
    val increment = AppStateStoreContext.useStore(AppState::increment)
    val decrement = AppStateStoreContext.useStore(AppState::decrement)

    Typography {
        variant = TypographyVariant.h2

        +"Current Count : $count"

        ButtonGroup {
            fullWidth = true

            Button {
                onClick = {
                    increment()
                }
                +"Increment"
            }

            Button {
                onClick = {
                    decrement()
                }
                +"Decrement"
            }
        }
    }
}