enum class ExitCode(val value: Int) {
    Success(0),
    PrintHelp(1),
    IncorrectLoginFormat(2),
    UnknownLogin(3),
    WrongPassword(4),
    UnknownRole(5),
    NoAccess(6),
    IncorrectActivity(7)
}
