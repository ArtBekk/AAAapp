@file:JvmName("Main")

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import services.getDBConnection
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val logger: Logger = LogManager.getLogger()

    logger.info("Initializing DAL")
    val dal = DataAccessLayer(getDBConnection(System.getenv("URL"), System.getenv("LOGIN"), System.getenv("PASS")))
    logger.info("Initialized DAL")

    logger.info("Initializing Handler")
    val handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)
    logger.info("Initialized Handler")

    logger.info("Initializing an app instance")
    val app = App(dal, handler)
    logger.info("Initialized the app instance")

    logger.info("Launching services")
    val result = app.run()
    logger.info("Launched services successfully")

    exitProcess(result)
}