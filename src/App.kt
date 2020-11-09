import services.*

class App(args: Array<String>) {
    private val handler: Handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)

    private var result: ExitCode = ExitCode.Success

    private fun authenticationNeeded(): Boolean = handler.login != null && handler.password != null
    private fun authorizationNeeded(): Boolean = authenticationNeeded() && handler.res != null && handler.role != null && result == ExitCode.Success
    private fun accountingNeeded(): Boolean = authorizationNeeded() && handler.ds != null && handler.de != null && handler.vol != null && result == ExitCode.Success

    fun run(): Int {
        if (authenticationNeeded())
            result = authenticate(handler)
        if (authorizationNeeded())
            result = authorize(handler)
        if (accountingNeeded())
            result = account(handler)
        return result.value
    }
}
