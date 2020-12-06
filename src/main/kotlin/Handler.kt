import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Handler(args: Array<String>) {

    private val logger: Logger = LogManager.getLogger()

    private val parser = ArgParser("AAAapp")

    var login: String? by parser.option(ArgType.String, shortName = "login", description = "User login transfer")
    var password: String? by parser.option(ArgType.String, shortName = "pass", description = "User password transfer")
    var res: String? by parser.option(ArgType.String, shortName = "res", description = "Transfer of resources")
    var role: String? by parser.option(ArgType.String, shortName = "role", description = "Specifying the user role")
    var ds: String? by parser.option(ArgType.String, shortName = "ds", description = "User start date")
    var de: String? by parser.option(ArgType.String, shortName = "de", description = "User end date")
    var vol: String? by parser.option(ArgType.String, shortName = "vol", description = "Entering the data size")

    fun authenticationNeeded(): Boolean = login != null && password != null
    fun authorizationNeeded(): Boolean = authenticationNeeded() && res != null && role != null
    fun accountingNeeded(): Boolean = authorizationNeeded() && ds != null && de != null && vol != null

    init {
        try {
            parser.parse(args)
        } catch (e: java.lang.IllegalStateException) {
        }
        logger.info("Parsed arguments successfully")
    }
}
