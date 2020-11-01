import services.*

class App() {
    fun run(args: Array<String>): ExitCode {
        var result: ExitCode = ExitCode.PRINTHELP
        val handler: Handler = if (args.isNullOrEmpty())
            Handler(arrayOf("-h"))
        else
            Handler(args)
        if (handler.login != null && handler.password != null) {
            result = authenticate(handler)
            if (handler.res != null && handler.role != null && result == ExitCode.SUCCESS) {
                result = authorize(handler)
                if (handler.vol != null && result == ExitCode.SUCCESS)
                    result = account(handler)
            }
        }
        return result
    }
}