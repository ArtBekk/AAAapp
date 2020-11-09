import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val app = App(args)
    exitProcess(app.run())
}
