package pages

import AppState
import AppStateStoreContext
import httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.promise
import models.GuGuDan
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import useTheme
import web.cssom.pct
import zustand.wrappers.useStore

val GuGuDanPage = FC {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val gugudan = AppStateStoreContext.useStore(AppState::gugudan)
    val setGuGuDan = AppStateStoreContext.useStore(AppState::setGuGuDan)
    val theme = useTheme()

    if (gugudan == null) {
        Typography {
            +"GuGuDan is not initialized yet"
        }
        Button {
            onClick = {
                coroutineScope.promise {
                    val response = httpClient.get("/api/gugudan")
                    setGuGuDan(response.body<GuGuDan>())
                }
            }
            +"Get GuGuDan"
        }

        return@FC
    }

    Typography {
        variant = TypographyVariant.h3
        sx {
            marginBottom = theme.spacing(5)
        }

        +gugudan.title
    }

    gugudan.table.entries.chunked(4).forEach { chunk ->
        Grid {
            container = true
            spacing = responsive(2)
            sx {
                width = 100.pct
            }
            chunk.forEach { entry ->
                val dan = entry.key
                val results = entry.value.entries.sortedBy { it.key }

                Grid {
                    item = true
                    this.asDynamic().xs = 3

                    TableContainer {
                        component = Paper

                        Table {
                            TableHead {
                                TableRow {
                                    TableCell {
                                        align = TableCellAlign.center
                                        colSpan = 2

                                        +"$dan"
                                    }
                                }
                            }
                            TableBody {
                                results.forEach { result ->
                                    val p = result.key
                                    val r = result.value

                                    TableRow {
                                        TableCell {
                                            align = TableCellAlign.center
                                            +"$p"
                                        }
                                        TableCell {
                                            align = TableCellAlign.right
                                            +"$r"
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        Divider {

            sx {
                marginTop = theme.spacing(2)
                marginBottom = theme.spacing(2)
            }
        }
    }
}