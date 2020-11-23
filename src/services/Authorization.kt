package services

import DataAccessLayer
import ExitCode
import Handler
import org.apache.logging.log4j.*
import models.Roles

fun authorize(handler: Handler, dal: DataAccessLayer): ExitCode {

    val logger: Logger = LogManager.getLogger()

    fun isResSubsidiary(resFromDB: String): Boolean {
        val name: List<String> = resFromDB.split('.')
        var result = true
        val splitInput = handler.res!!.split('.')
        for (i in name.indices) {
            if (i < splitInput.size) {
                if (splitInput[i] != name[i]) result = false
            } else break
        }
        return result
    }

    logger.info("Checking the user role existence.")
    if (!Roles.contains(handler.role!!)) {
        logger.info("Invalid role.")
        return ExitCode.UnknownRole
    } else logger.info("The role was entered correctly.")

    logger.info("Checking user access to a resource.")
    logger.info("Requesting data from the database.")
    return if (dal.getUserAccessInfo(handler.login!!, handler.role!!).any { isResSubsidiary(it) }){
        logger.info("Access to the resource is allowed.")
        ExitCode.Success
    } else {
        logger.info("Access is denied.")
        ExitCode.NoAccess
    }
}