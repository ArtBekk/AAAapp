import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

class Handler(args: Array<String>) {

    var login: String? = ""
    var password: String? = ""
    var res: String? = ""
    var role: String? = ""
    var ds: String? = ""
    var de: String? = ""
    var vol: String? = ""


    init {

        try {
            val parser = ArgParser("handler")

            login = parser.option(ArgType.String, shortName = "login",
                    description = "User login transfer").value
            password = parser.option(ArgType.String, shortName = "pass",
                    description = "User password transfer").value
            res = parser.option(ArgType.String, shortName = "res",
                    description = "Transfer of resources").value
            role = parser.option(ArgType.String, shortName = "role",
                    description = "Specifying the user role").value
            ds = parser.option(ArgType.String, shortName = "ds",
                    description = "User start date").value
            de = parser.option(ArgType.String, shortName = "de",
                    description = "User end date").value
            vol = parser.option(ArgType.String, shortName = "vol",
                    description = "Entering the data size").value

            parser.parse(args)
        } catch (exp: IllegalStateException) {
        }

    }
}