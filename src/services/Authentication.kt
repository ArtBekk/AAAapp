package services

import DataAccessLayer
import ExitCode
import Handler
import logger
import java.security.MessageDigest

fun authenticate(handler: Handler, dal: DataAccessLayer): ExitCode {

    fun hash(input: String): String {
        logger.info("Hashing password...")
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    logger.info("Checking login format...")
    if (handler.login!!.matches(".+[^a-zA-Z0-9]".toRegex())) {
        logger.info("Incorrect login format.")
        return ExitCode.IncorrectLoginFormat
    }

    logger.info("Login format is correct.")

    if (dal.userExists(handler.login!!)) {
        val user = dal.getUser(handler.login!!)
        return if (user.hash == hash(hash(handler.password!!) + user.salt))
            ExitCode.Success
        else
            ExitCode.WrongPassword
    }
    return ExitCode.UnknownLogin
}
