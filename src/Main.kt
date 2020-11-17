import kotlin.system.exitProcess

fun main(args: Array<String>) {
    logger.info("Initializing an app instance")
    val app = App(args)
    logger.info("Initialized the app instance")
    exitProcess(app.run())
}
