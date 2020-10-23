package Services

import ExitCode
import Handler
import models.Roles
import Users

fun authorization(handler: Handler): ExitCode{

    if (handler.role == Roles.WRITE.rolesName ||
            handler.role == Roles.EXECUTE.rolesName ||
            handler.role == Roles.READ.rolesName){
        Users.forEach {
        }



    }
    else return ExitCode.UNKNOWNROLE

}
