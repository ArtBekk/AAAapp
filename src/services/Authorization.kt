package services

import DataAccessLayer
import ExitCode
import Handler
import models.possibleRoles

fun authorize(handler: Handler, dal: DataAccessLayer): ExitCode {

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

    if (!possibleRoles.contains(handler.role))
        return ExitCode.UnknownRole

    return if (dal.getUserAccessInfo(handler).any { isResSubsidiary(it) })
        ExitCode.Success
    else
        ExitCode.NoAccess
}