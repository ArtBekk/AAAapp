import kotlin.system.exitProcess
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main(args: Array<String>) {
    val logger: Logger = LogManager.getLogger()
    val app = App(args)
    exitProcess(app.run())
}
