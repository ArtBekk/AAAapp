import services.*

class App() {
    fun run(args: Array<String>): ExitCode {
        val handler = Handler(args)
        var result: ExitCode
        /*
        Checks login and password parameters and authenticates
         */
        result = if (handler.login != "") {
            if (handler.password != "")
                authenticate(handler)
            else
                ExitCode.WRONGPASSWORD
        } else ExitCode.UNKNOWNLOGIN
        /*
        Authorization
        */
        if (handler.res != "" && result == ExitCode.SUCCESS)
            result = authorize(handler)
        /*
        Accounting
        */
        if (handler.vol != "" && result == ExitCode.SUCCESS)
            result = account(handler)
        return result
    }
}