package services

import ExitCode
import Handler
import domains.Users

fun authenticate(handler: Handler): ExitCode {

    if (handler.login!!.matches(".+[a-zA-Z0-9]".toRegex()))
        return ExitCode.INCORRECTLOGINFORMAT
    Users.forEach {
        if (it.login == handler.login) {
            return if (it.password == hashSalt(handler.password!!))
                ExitCode.SUCCESS
            else
                ExitCode.WRONGPASSWORD
        }
    }
    return ExitCode.UNKNOWNLOGIN
}

fun hashSalt(input: String): Int = (input + "someSalt").hashCode()