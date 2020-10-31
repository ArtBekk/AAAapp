package services

import ExitCode
import Handler
import domains.Users
import models.Roles

fun authorize(handler: Handler): ExitCode {

    if (handler.role == Roles.WRITE.rolesName ||
            handler.role == Roles.EXECUTE.rolesName ||
            handler.role == Roles.READ.rolesName) {

        Users.forEach {
            if (it.login == handler.login)
                return if (it.hasAccess(handler.res!!)) {
                    ExitCode.SUCCESS
                } else ExitCode.NOACCESS
        }
    } else return ExitCode.UNKNOWNROLE

    return ExitCode.SUCCESS
}
