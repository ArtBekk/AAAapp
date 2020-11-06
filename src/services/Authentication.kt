package services

import ExitCode
import Handler
import domains.Users
import java.security.MessageDigest

fun authenticate(handler: Handler): ExitCode {

    fun hash(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    if (handler.login!!.matches(".+[^a-zA-Z0-9]".toRegex()))
        return ExitCode.IncorrectLoginFormat

    if (Users.any { it.login == handler.login!! }) {
        return if (Users.any { it.password == hash(hash(handler.password!!) + "someSalt") })
            ExitCode.Success
        else
            ExitCode.WrongPassword
    }
    return ExitCode.UnknownLogin
}
