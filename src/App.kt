import services.*

class App(args: Array<String>) {

    private val handler: Handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)

    private var result: ExitCode = ExitCode.Success

    fun run(): Int {
        if (handler.authenticationNeeded()) {
            logger.info("STEP 1: Authentication")
            result = authenticate(handler)
            logger.info("Authentication status: $result")
        }
        if (handler.authorizationNeeded() && result == ExitCode.Success) {
            logger.info("STEP 2: Authorization")
            result = authorize(handler)
            logger.info("Authorization status: $result")
        }

        if (handler.accountingNeeded() && result == ExitCode.Success) {
            logger.info("STEP 3: Accounting.")
            result = account(handler)
            logger.info("Accounting status: $result")
        }
        return result.value
    }
}
