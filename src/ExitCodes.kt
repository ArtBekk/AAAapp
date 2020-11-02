enum class ExitCode(val value: Int) {
    SUCCESS(0),
    PRINTHELP(1),
    INCORRECTLOGINFORMAT(2),
    UNKNOWNLOGIN(3),
    WRONGPASSWORD(4),
    UNKNOWNROLE(5),
    NOACCESS(6),
    INCORRECTACTIVITY(7)
}

fun main(){
    println(ExitCode.SUCCESS.value)
}
