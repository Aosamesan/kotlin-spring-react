import js.objects.jso
import mui.icons.material.Home
import mui.icons.material.Numbers
import mui.icons.material.Person
import mui.icons.material.Textsms
import pages.*
import react.create
import react.router.dom.createBrowserRouter

val appRouter = createBrowserRouter(arrayOf(
    jso {
        path = "/"
        element = Dashboard.create {
            menuList = listOf(
                jso {
                    path = "/"
                    text = "Home"
                    Icon = Home
                },
                jso {
                    path = "/counter"
                    text = "Counter"
                    Icon = Numbers
                },
                jso {
                    path = "/echo"
                    text = "Echo"
                    Icon = Person
                },
                jso {
                    path = "/gugudan"
                    text = "GuGuDan"
                    Icon = Numbers
                },
                jso {
                    path = "/complex"
                    text = "Complex Data"
                    Icon = Textsms
                }
            )
        }
        children = arrayOf(
            jso {
                element = HomePage.create()
                index = true
            },
            jso {
                path = "counter"
                element = CounterPage.create()
            },
            jso {
                path = "echo"
                element = EchoPage.create()
            },
            jso {
                path = "gugudan"
                element = GuGuDanPage.create()
            },
            jso {
                path = "complex"
                element = ComplexDataPage.create()
            }
        )
    }
))