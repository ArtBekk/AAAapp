package models

import Handler
import models.Roles

class User(val login: String, val password: Int,
           private val write: Array<Resource>?,
           private val read: Array<Resource>?,
           private val execute: Array<Resource>?) {

    fun hasAccess(handler: Handler): Boolean {
        return when (handler.role) {
            Roles.WRITE.rolesName -> {
                write.contains(handler.res)
            }
            Roles.READ.rolesName -> {
                read.contains(handler.res)
            }
            Roles.EXECUTE.rolesName -> {
                execute.contains(handler.res)
            }
            else -> false
        }
    }

    fun Array<Resource>?.contains(input: String): Boolean {
        this?.forEach { x ->
            return x.contains(input)
        }
        return false
    }

}