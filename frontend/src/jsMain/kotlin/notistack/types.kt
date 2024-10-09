@file:Suppress("unused", "PropertyName")

package notistack

import react.*
import react.dom.events.SyntheticEvent
import react.dom.html.HTMLAttributes
import web.html.HTMLDivElement
import web.html.HTMLElement

external interface TransitionHandlerProps {
    var onEnter: ((node: HTMLElement, isAppearing: Boolean, key: dynamic) -> Unit)?
    var onEntered: ((node: HTMLElement, isAppearing: Boolean, key: dynamic) -> Unit)?
    var onExit: ((node: HTMLElement, key: dynamic) -> Unit)?
    var onExited: ((node: HTMLElement, key: dynamic) -> Unit)?
}

external interface TransitionDuration {
    var enter: Number?
    var exit: Number?
}

typealias SlideTransitionDirection = String

object SlideTransitionDirections {
    const val DOWN: SlideTransitionDirection = "down"
    const val LEFT: SlideTransitionDirection = "left"
    const val RIGHT: SlideTransitionDirection = "right"
    const val UP: SlideTransitionDirection = "up"
}

external interface TransitionProps : TransitionHandlerProps {
    var appear: Boolean?
    var `in`: Boolean?

    @JsName("timeout")
    var timeoutNumber: Number?

    @JsName("timeout")
    var timeoutDuration: TransitionDuration?
    var enter: Boolean?
    var exit: Boolean?
    var mountOnEnter: Boolean?
    var unmountOnExit: Boolean?
    var style: CSSProperties?
    var direction: SlideTransitionDirection?

    var onEntering: ((node: HTMLElement, isAppearing: Boolean) -> Unit)?
    var onExiting: ((node: HTMLElement) -> Unit)?

    var addEventListener: ((node: HTMLElement, callback: () -> Unit) -> Unit)?
}

external interface TransitionPropsWithChildren : TransitionProps, PropsWithChildren


typealias SnackbarOriginVertical = String
typealias SnackbarOriginHorizontal = String

object SnackbarOrigins {
    object Vertical {
        const val TOP: SnackbarOriginVertical = "top"
        const val BOTTOM: SnackbarOriginVertical = "bottom"
    }

    object Horizontal {
        const val LEFT: SnackbarOriginHorizontal = "left"
        const val CENTER: SnackbarOriginHorizontal = "center"
        const val RIGHT: SnackbarOriginHorizontal = "right"
    }
}

external interface SnackbarOrigin {
    // 'top', 'bottom'
    var vertical: SnackbarOriginVertical

    // 'left', 'center', 'right'
    var horizontal: SnackbarOriginHorizontal
}

external interface SharedProps<V> : TransitionHandlerProps {
    var className: String?
    var style: CSSProperties?
    var anchorOrigin: SnackbarOrigin?
    var autoHideDuration: Number?
    var disableWindowBlurListener: Boolean?
    var TransitionComponent: ReactElement<TransitionPropsWithChildren>
    var TransitionProps: TransitionProps?
    var variant: V?
    var preventDuplicate: Boolean?
    var action: ReactNode?

    @JsName("action")
    var actionByKey: ((key: dynamic) -> ReactNode)?
    var hideIconVariant: Boolean?
    var SnackbarProps: HTMLAttributes<HTMLDivElement>?
    var content: ReactNode?

    @JsName("content")
    var contentStringMessageCallback: ((key: String, message: String?) -> ReactNode)?

    @JsName("content")
    var contentReactNodeMessageCallback: ((key: String, message: ReactNode?) -> ReactNode)?

    var onClose: ((event: SyntheticEvent<*, *>?, reason: String, key: Any?) -> Unit)?
}

external interface OptionObject<V> : SharedProps<V> {
    @JsName("key")
    var stringKey: String?

    @JsName("key")
    var numberKey: Number?

    var persist: Boolean?
}

typealias BaseVariant = String

object BaseVariants {
    const val DEFAULT: BaseVariant = "default"
    const val ERROR: BaseVariant = "error"
    const val SUCCESS: BaseVariant = "success"
    const val WARNING: BaseVariant = "warning"
    const val INFO: BaseVariant = "info"
}

external interface SnackbarProviderProps : SharedProps<BaseVariant>, PropsWithChildren {
    var dense: Boolean?
    var maxSnack: Int?
    var domRoot: HTMLElement?
    var classes: Map<String, String>?
    var iconVariant: Map<BaseVariant, ReactNode>?
    var ref: Ref<FC<SnackbarProviderProps>>?
    var Components: Map<BaseVariant, ReactNode>?
}

external interface OptionObjectWithStringMessage<V> : OptionObject<V> {
    var message: String?
}

external interface OptionObjectWithReactMessage<V> : OptionObject<V> {
    var message: ReactNode?
}

external interface EnqueueSnackbar {
    @JsName("call")
    fun <V> invoke(self: dynamic = definedExternally, options: OptionObjectWithStringMessage<V>): Any

    @JsName("call")
    fun <V> invoke(self: dynamic = definedExternally, options: OptionObjectWithReactMessage<V>): Any

    @JsName("call")
    fun <V> invoke(
        self: dynamic = definedExternally,
        message: String,
        options: OptionObject<V>? = definedExternally
    ): Any

    @JsName("call")
    fun <V> invoke(
        self: dynamic = definedExternally,
        message: ReactNode,
        options: OptionObject<V>? = definedExternally
    ): Any
}

external interface CloseSnackbar {
    @JsName("call")
    fun invoke(self: dynamic = definedExternally, key: String? = definedExternally)

    @JsName("call")
    fun invoke(self: dynamic = definedExternally, key: Number)
}

external interface ProviderContext {
    val enqueueSnackbar: EnqueueSnackbar
    val closeSnackbar: CloseSnackbar
}