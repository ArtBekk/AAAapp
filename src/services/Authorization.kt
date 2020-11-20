package services

import DataAccessLayer
import ExitCode
import Handler
import models.Access
import models.Roles

fun authorize(handler: Handler, dal: DataAccessLayer): ExitCode {

    if (!Roles.values().toString().contains(handler.role!!))
        return ExitCode.UnknownRole

    return if (dal.getUserAccessInfo(handler))
        ExitCode.Success
    else
        ExitCode.NoAccess
}