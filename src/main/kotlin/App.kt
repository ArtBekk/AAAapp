import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import services.account
import services.authenticate
import services.authorize

class App(private val dal: DataAccessLayer, private val handler: Handler) {

    private val logger: Logger = LogManager.getLogger()

    private var result: ExitCode = ExitCode.Success

    fun run(): Int {
        if (handler.authenticationNeeded()) {
            logger.info("STEP 1: Authentication")
            result = authenticate(handler, dal)
            logger.info("Authentication status: $result")
        }
        if (handler.authorizationNeeded() && result == ExitCode.Success) {
            logger.info("STEP 2: Authorization")
            result = authorize(handler, dal)
            logger.info("Authorization status: $result")
        }
        if (handler.accountingNeeded() && result == ExitCode.Success) {
            logger.info("STEP 3: Accounting.")
            result = account(handler, dal)
            logger.info("Accounting status: $result")
        }
        dal.closeConnection()
        return result.value
    }
}
