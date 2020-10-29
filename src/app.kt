import services.*
import kotlin.system.exitProcess

class App() {
    fun run(args: Array<String>): ExitCode {
        val handler = Handler(args)
        var result: ExitCode
        /*
        Checks login and password parameters and authenticates
         */
        if (handler.login != "") {
            if (handler.password != "")
                result = services.authenticate(handler)
            else
                result = ExitCode.WRONGPASSWORD
        } else result = ExitCode.UNKNOWNLOGIN
        /*
        Authorization
        */
        if (handler.res != "" && result == ExitCode.SUCCESS)
            result = authorization(handler)
        /*
        Accounting
        */
        if (handler.vol != "" && result == ExitCode.SUCCESS)
            result = accounting(handler)
        return result
    }
}