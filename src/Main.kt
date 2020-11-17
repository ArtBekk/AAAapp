import kotlin.system.exitProcess
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger()

fun main(args: Array<String>) {
    logger.info("Initializing an app instance")
    val app = App(args)
    logger.info("Initialized the app instance")
    exitProcess(app.run())
}
