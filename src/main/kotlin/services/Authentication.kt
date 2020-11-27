package services

import DataAccessLayer
import ExitCode
import Handler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.security.MessageDigest

fun authenticate(handler: Handler, dal: DataAccessLayer): ExitCode {

    val logger: Logger = LogManager.getLogger()

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

    logger.info("Search for a user in the database.")
    if (dal.userExists(handler.login!!)) {
        logger.info("The user with the specified username was found.")
        logger.info("Requesting data from the database.")
        val user = dal.getUser(handler.login!!)
        logger.info("Checking the user password.")
        return if (user.hash == hash(hash(handler.password!!) + user.salt)) {
            logger.info("Password is correct.")
            ExitCode.Success
        } else {
            logger.info("Incorrect password.")
            ExitCode.WrongPassword
        }
    }
    logger.info("The user with the specified username was not found.")
    return ExitCode.UnknownLogin
}