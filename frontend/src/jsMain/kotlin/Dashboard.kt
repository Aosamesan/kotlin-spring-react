import csstype.Properties
import emotion.styled.styled
import js.objects.Object
import js.objects.jso
import mui.icons.material.ChevronLeft
import mui.icons.material.ChevronRight
import mui.icons.material.SvgIconComponent
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.IntrinsicType
import react.Props
import react.dom.aria.ariaLabel
import react.dom.html.HTMLAttributes
import react.router.Outlet
import react.router.dom.Link
import react.router.dom.LinkProps
import react.useState
import web.cssom.*
import web.html.HTMLElement
import zustand.wrappers.useStore
import mui.material.AppBarProps as MuiAppBarProps

external interface MenuEntry {
    var text: String

    @Suppress("PropertyName")
    var Icon: SvgIconComponent
    var path: String
}

external interface DrawerOpenProps : Props {
    var open: Boolean?
}

external interface DashboardProps : Props {
    var menuList: Iterable<MenuEntry>
}

external interface DrawerOpenPropsForRawHTML : DrawerOpenProps, Props, HTMLAttributes<HTMLElement>

external interface AppBarProps : DrawerOpenProps, MuiAppBarProps

private const val drawerWidth = 250

private val MainContents = IntrinsicType<DrawerOpenPropsForRawHTML>("main").styled<DrawerOpenPropsForRawHTML> { props ->
    val theme = useTheme()

    padding = theme.spacing(3)
    flexGrow = number(1.0)

    if (props.open == true) {
        transition = theme.transitions.create(arrayOf("margin"), jso {
            easing = theme.transitions.easing.easeOut
            duration = theme.transitions.duration.enteringScreen
        })
        marginLeft = 0.px
    } else {
        transition = theme.transitions.create(arrayOf("margin"), jso {
            easing = theme.transitions.easing.sharp
            duration = theme.transitions.duration.leavingScreen
        })
        marginLeft = -drawerWidth.px
    }
}

val DrawerHeader = styled(IntrinsicType<DrawerOpenPropsForRawHTML>("div"))() {
    val theme = useTheme()
    Object.assign(jso<Properties> {
        display = Display.flex
        alignItems = AlignItems.center
        padding = theme.spacing(0, 1)
        justifyContent = JustifyContent.flexEnd
    }, theme.mixins.toolbar.unsafeCast<Any?>()).unsafeCast<Properties>()
}

val AppBar = mui.material.AppBar.styled<AppBarProps> { props ->
    val theme = useTheme()
    if (props.open == false) {
        transition = theme.transitions.create(arrayOf("width", "margin"), jso {
            easing = theme.transitions.easing.sharp
            duration = theme.transitions.duration.leavingScreen
        })
    } else {
        width = "calc(100% - ${drawerWidth}px)".unsafeCast<Width>()
        marginLeft = drawerWidth.px
        transition = theme.transitions.create(arrayOf("width", "margin"), jso {
            easing = theme.transitions.easing.easeOut
            duration = theme.transitions.duration.enteringScreen
        })
    }
}

val Dashboard = FC<DashboardProps> { props ->
    val theme = useTheme()
    val (openDrawer, setOpenDrawer) = useState(false)
    val state = AppStateStoreContext.useStore()

    Box {
        sx { display = Display.flex }
        CssBaseline()

        AppBar {
            position = AppBarPosition.fixed
            `open` = openDrawer

            Toolbar {
                IconButton {
                    color = IconButtonColor.inherit
                    ariaLabel = "open drawer"
                    onClick = { setOpenDrawer(true) }
                    edge = IconButtonEdge.start
                    sx {
                        marginRight = 2.px
                        if (openDrawer) {
                            display = "none".unsafeCast<Display>()
                        }
                    }

                    mui.icons.material.Menu()
                }
                Typography {
                    variant = TypographyVariant.h6
                    +"Spring React Fullstack App"
                }
            }
        }
        Drawer {
            sx {
                width = drawerWidth.px
                flexShrink = number(0.0)
                this.asDynamic()["& .MuiDrawer-paper"] = jso {
                    this.width = drawerWidth.px
                    this.boxSizing = BoxSizing.borderBox
                }
            }
            variant = DrawerVariant.persistent
            anchor = DrawerAnchor.left
            open = openDrawer

            DrawerHeader {
                IconButton {
                    onClick = {
                        setOpenDrawer(false)
                    }
                    if (theme.direction == Direction.ltr) {
                        ChevronLeft()
                    } else {
                        ChevronRight()
                    }
                }
            }
            Divider()
            List {
                props.menuList.forEach { entry ->
                    ListItem {
                        key = entry.path
                        disableGutters = true

                        ListItemButton {
                            component = Link
                            onClick = { setOpenDrawer(false) }
                            this.unsafeCast<LinkProps>().to = entry.path

                            ListItemIcon {
                                entry.Icon()
                            }
                            ListItemText {
                                +entry.text
                            }
                        }
                    }
                }
                Divider()
                ThemeSwitcher()
            }
        }
        MainContents {
            open = openDrawer
            onClick = { setOpenDrawer(false) }
            DrawerHeader()
            Outlet()
        }
    }
}