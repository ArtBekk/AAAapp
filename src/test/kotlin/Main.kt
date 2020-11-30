@file:JvmName("Main")

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val appTest = AppTest()

    val logger: Logger = LogManager.getLogger()
    logger.info("Initializing an app instance")
    val app = App(args)
    logger.info("Initialized the app instance")
    exitProcess(app.run())
}
