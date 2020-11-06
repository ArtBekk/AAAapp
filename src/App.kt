import services.*

class App() {
    fun run(args: Array<String>): ExitCode {
        var result: ExitCode = ExitCode.PrintHelp
        val handler: Handler = if (args.isNullOrEmpty())
            Handler(arrayOf("-h"))
        else
            Handler(args)
        if (handler.login != null && handler.password != null)
            result = authenticate(handler)
        if (handler.res != null && handler.role != null && result == ExitCode.Success)
            result = authorize(handler)
        if (handler.ds != null && handler.de != null && handler.vol != null && result == ExitCode.Success)
            result = account(handler)
        return result
    }
}
