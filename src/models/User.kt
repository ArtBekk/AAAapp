package models

class User(val login: String,
           val password: String,
           private val write: List<Resource>,
           private val read: List<Resource>,
           private val execute: List<Resource>) {

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

    fun List<Resource>.contains(input: String): Boolean {
        return this.any { it.contains(input) }
    }

}
