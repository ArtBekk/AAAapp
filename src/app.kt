import services.*

class App() {
    fun run(args: Array<String>): ExitCode {
        val handler = Handler(args)
        var result: ExitCode = ExitCode.PRINTHELP
        if (handler.login != null && handler.password != null) {
            result = authenticate(handler)
            if (handler.res != null && result == ExitCode.SUCCESS) {
                result = authorize(handler)
                if (handler.vol != null && result == ExitCode.SUCCESS)
                    result = account(handler)
            }
        }
        return result
    }
}