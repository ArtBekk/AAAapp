import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

class Handler(args: Array<String>) {

    var login: String = ""
    var password: String = ""
    var res: String = ""
    var role: String = ""
    var ds: String = ""
    var de: String = ""
    var vol: String = ""


    init {

        try {
            val parser = ArgParser("handler")

            login = parser.option(ArgType.String, shortName = "login",
                    description = "User login transfer").toString()
            password = parser.option(ArgType.String, shortName = "pass",
                    description = "User password transfer").toString()
            res = parser.option(ArgType.String, shortName = "res",
                    description = "Transfer of resources").toString()
            role = parser.option(ArgType.String, shortName = "role",
                    description = "Specifying the user role").toString()
            ds = parser.option(ArgType.String, shortName = "ds",
                    description = "User start date").toString()
            de = parser.option(ArgType.String, shortName = "de",
                    description = "User end date").toString()
            vol = parser.option(ArgType.String, shortName = "vol",
                    description = "Entering the data size").toString()

            parser.parse(args)
        } catch (exp: IllegalStateException) {
        }

    }
}