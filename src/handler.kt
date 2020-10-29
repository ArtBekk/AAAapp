class Handler(args: Array<String>) {

    init {
        try {
            args.forEach {
                when (it) {
                    "-login" -> login = args[args.indexOf(it) + 1]
                    "-pass" -> password = args[args.indexOf(it) + 1]
                    "-res" -> res = args[args.indexOf(it) + 1]
                    "-role" -> role = args[args.indexOf(it) + 1]
                    "-ds" -> ds = args[args.indexOf(it) + 1]
                    "-de" -> de = args[args.indexOf(it) + 1]
                    "-vol" -> vol = args[args.indexOf(it) + 1]
                    "-h" -> helpNeeded = true

                }
            }
        } catch (e: IndexOutOfBoundsException) {
        }


    }

    var login: String = ""
    var password: String = ""
    var res: String = ""
    var role: String = ""
    var ds: String = ""
    var de: String = ""
    var vol: String = ""
    var helpNeeded: Boolean = false
}