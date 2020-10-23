package models

import Handler

class User(val login: String, val password: Int,
           private val write: Array<Resource>?,
           private val read: Array<Resource>?,
           private val execute: Array<Resource>?) {

    fun hasAccess(handler: Handler): Boolean {
        return when (handler.role) {
            "WRITE" -> {
                write.contains(handler.res)
            }
            "READ" -> {
                read.contains(handler.res)
            }
            "EXECUTE" -> {
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