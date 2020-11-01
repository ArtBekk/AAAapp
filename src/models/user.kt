package models

class User(val login: String,
           val password: String,
           private val write: Array<Resource>?,
           private val read: Array<Resource>?,
           private val execute: Array<Resource>?) {

    fun hasAccess(role: String, res: String): Boolean {
        return when (role) {
            Roles.WRITE.rolesName -> {
                write.contains(res)
            }
            Roles.READ.rolesName -> {
                read.contains(res)
            }
            Roles.EXECUTE.rolesName -> {
                execute.contains(res)
            }
            else -> false
        }
    }

    fun Array<Resource>?.contains(input: String): Boolean {
        this?.forEach { x ->
            if (x.contains(input))
                return true
        }
        return false
    }

}