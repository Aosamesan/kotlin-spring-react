import Themes.unaryPlus
import js.objects.jso
import mui.icons.material.DarkMode
import mui.icons.material.LightMode
import mui.material.*
import mui.material.styles.Theme
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import react.*
import react.dom.aria.ariaLabel

external interface ThemeContextProviderProps : PropsWithChildren {
    var theme: Theme
}

object Themes {
    private val typography: dynamic = jso {
        fontFamily = arrayOf("Noto Sans KR", "sans-serif").joinToString(",")
    }

    val Light = createTheme(jso {
        palette = jso {
            mode = PaletteMode.light
        }
        typography = this@Themes.typography
    })
    val Dark = createTheme(jso {
        palette = jso {
            mode = PaletteMode.dark
        }
        typography = this@Themes.typography
    })

    operator fun get(mode: String) = when (mode) {
        "light" -> Light
        "dark" -> Dark
        else -> throw IllegalArgumentException("Unknown theme mode: $mode")
    }

    operator fun Theme.unaryPlus(): String = when (this) {
        Light -> "light"
        Dark -> "dark"
        else -> throw IllegalArgumentException("Unknown theme: $this")
    }
}

val ThemeContext: RequiredContext<StateInstance<Theme>> = createRequiredContext()

fun useTheme() = useRequiredContext(ThemeContext).component1()

fun useSetTheme() = useRequiredContext(ThemeContext).component2()

val ThemeContextProvider = FC<ThemeContextProviderProps> { props ->
    val state = useState(props.theme)

    ThemeContext(state) {
        ThemeProvider {
            theme = state.component1()

            CssBaseline()
            +props.children
        }
    }
}

val ThemeSwitcher = FC {
    val theme = useTheme()
    val setTheme = useSetTheme()

    ToggleButtonGroup {
        fullWidth = true
        color = ToggleButtonGroupColor.primary
        value = +theme
        exclusive = true
        onChange = { _, mode ->
            if (mode is String) {
                setTheme(Themes[mode])
            }
        }
        ariaLabel = "theme switcher"

        ToggleButton {
            value = +Themes.Light
            ariaLabel = "light theme"

            LightMode()
        }
        ToggleButton {
            value = +Themes.Dark
            ariaLabel = "dark theme"

            DarkMode()
        }
    }
}