package services

import ExitCode
import Handler
import domains.Users
import models.Roles

fun authorize(handler: Handler): ExitCode {

    try {
        @Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
        val userRole = Roles.valueOf(handler.role!!)
        Users.forEach {
            if (it.login == handler.login) {
                return if (it.hasAccess(userRole, handler.res!!)) {
                    ExitCode.Success
                } else ExitCode.NoAccess
            }
        }

    } catch (exp: IllegalArgumentException) {
        return ExitCode.UnknownRole
    }
    return ExitCode.Success
}
