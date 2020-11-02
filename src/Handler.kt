import kotlinx.cli.*

class Handler(args: Array<String>) {

    private val parser = ArgParser("AAAapp")

    var login: String? by parser.option(ArgType.String, shortName = "login",
            description = "User login transfer")
    var password: String? by parser.option(ArgType.String, shortName = "pass",
            description = "User password transfer")
    var res: String? by parser.option(ArgType.String, shortName = "res",
            description = "Transfer of resources")
    var role: String? by parser.option(ArgType.String, shortName = "role",
            description = "Specifying the user role")
    var ds: String? by parser.option(ArgType.String, shortName = "ds",
            description = "User start date")
    var de: String? by parser.option(ArgType.String, shortName = "de",
            description = "User end date")
    var vol: String? by parser.option(ArgType.String, shortName = "vol",
            description = "Entering the data size")

    init {
        try {
            parser.parse(args)
        }
        catch(exp: Exception){
            Handler(arrayOf("-h"))
        }
    }
}
