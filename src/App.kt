import services.*

class App(args: Array<String>) {
    private val handler: Handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)

    private var result: ExitCode = ExitCode.Success

    fun run(): Int {
        if (handler.authenticationNeeded())
            result = authenticate(handler)
        if (handler.authorizationNeeded() && result == ExitCode.Success)
            result = authorize(handler)
        if (handler.accountingNeeded() && result == ExitCode.Success)
            result = account(handler)
        return result.value
    }
}
