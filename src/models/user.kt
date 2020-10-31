package models

class User(val login: String, val password: Int,
           private val write: Array<Resource>?,
           private val read: Array<Resource>?,
           private val execute: Array<Resource>?) {

    fun hasAccess(input: String): Boolean {
        return when (input) {
            Roles.WRITE.rolesName -> {
                write.contains(input)
            }
            Roles.READ.rolesName -> {
                read.contains(input)
            }
            Roles.EXECUTE.rolesName -> {
                execute.contains(input)
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