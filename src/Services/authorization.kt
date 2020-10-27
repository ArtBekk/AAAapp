package Services

import ExitCode
import Handler
import domains.Users
import models.Roles

fun authorization(handler: Handler): ExitCode{

    if (handler.role == Roles.WRITE.rolesName ||
            handler.role == Roles.EXECUTE.rolesName ||
            handler.role == Roles.READ.rolesName){

        Users.forEach {
            if (it.login == handler.login)
                if(it.hasAccess(handler)){
                    return ExitCode.SUCCESS
                }
            else return ExitCode.NOACCESS
        }
    }
    else return ExitCode.UNKNOWNROLE

    return ExitCode.SUCCESS
}
