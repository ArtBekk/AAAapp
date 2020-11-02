package models

class User(val login: String,
           val password: String,
           private val write: Array<Resource>,
           private val read: Array<Resource>,
           private val execute: Array<Resource>) {

    fun hasAccess(role: Roles, res: String): Boolean {

        return when (role) {
            Roles.WRITE -> {
                write.contains(res)
            }
            Roles.READ -> {
                read.contains(res)
            }
            Roles.EXECUTE -> {
                execute.contains(res)
            }
        }
    }

    fun Array<Resource>.contains(input: String): Boolean {
        return this.any{ it.contains(input) }
    }

}
