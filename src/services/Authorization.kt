package services

import DataAccessLayer
import ExitCode
import Handler
import models.Access
import models.Roles

fun authorize(handler: Handler, dal: DataAccessLayer): ExitCode {

    if (!Roles.values().toString().contains(handler.role!!))
        return ExitCode.UnknownRole

    val rights: List<Access> = dal.getUsersAccessInfo(handler.login!!)

    return if (rights.isResSubsidiary(handler.res!!))
        ExitCode.Success
    else
        ExitCode.NoAccess
}

fun List<Access>.isResSubsidiary(input: String): Boolean {
    return this.any { it.isResSubsidiary(input) }
}