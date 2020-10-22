package services

import ExitCode
import Handler
import Users

fun authenticate(handler: Handler): ExitCode {
    if (handler.login=="")
        return Ex

    Users.forEach {
        if (it.login == handler.login && it.password == hashSalt(handler.password)) {
            println("Success")
            return ExitCode.SUCCESS
        }
    }
    return false
}

fun hashSalt(input: String): Int = (input + "someSalt").hashCode()