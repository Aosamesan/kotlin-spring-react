package pages

import AppState
import AppStateStoreContext
import httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import js.objects.jso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.promise
import mui.material.Button
import mui.material.Typography
import mui.material.styles.TypographyVariant
import notistack.BaseVariants
import notistack.invoke
import notistack.useCloseSnackbar
import notistack.useEnqueueSnackbar
import react.FC
import react.create
import zustand.wrappers.useStore
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
val ComplexDataPage = FC {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val complexData = AppStateStoreContext.useStore(AppState::complexData)
    val setComplexData = AppStateStoreContext.useStore(AppState::setComplexData)
    val enqueueSnackbar = useEnqueueSnackbar()
    val closeSnackbar = useCloseSnackbar()

    if (complexData == null) {
        Typography {
            variant = TypographyVariant.h2
            gutterBottom = true
            +"Complex Data is not initialized yet"
        }
        Button {
            fullWidth = true
            onClick = {
                coroutineScope.promise {
                    val response = httpClient.get("/api/complex")
                    setComplexData(response.body())
                }
            }
            +"Get Complex Data"
        }
        Button {
            fullWidth = true
            onClick = {
                enqueueSnackbar("Success", jso {
                    variant = BaseVariants.SUCCESS
                })
            }
            +"Success Snackbar"
        }
        Button {
            fullWidth = true
            onClick = {
                val key = Uuid.random().toHexString()
                enqueueSnackbar(FC {
                    Typography {
                        variant = TypographyVariant.h4
                        +"Error"
                    }
                    Button {
                        onClick = {
                            closeSnackbar(key)
                        }
                        +"Close"
                    }
                }.create(), jso {
                    variant = BaseVariants.ERROR
                    stringKey = key
                })
            }
            +"Error Snackbar"
        }
        return@FC
    }

    Typography {
        variant = TypographyVariant.h3
        gutterBottom = true
        +"Title"
    }
    Typography {
        variant = TypographyVariant.body1
        gutterBottom = true
        +complexData.title
    }
    Typography {
        variant = TypographyVariant.h5
        gutterBottom = true
        +"Message"
    }
    Typography {
        variant = TypographyVariant.body2
        +complexData.simple.text
    }
}