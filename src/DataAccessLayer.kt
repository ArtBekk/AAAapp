import models.User
import java.sql.Connection

class DataAccessLayer(private val connection: Connection) {

    fun userExists(handler: Handler): Boolean {
        var userExists = false
        val searchUser = connection.prepareStatement("SELECT* FROM users WHERE login = ?")
        searchUser.setString(1, handler.login)
        val queryResult = searchUser.executeQuery()
        if (queryResult.next()) userExists = true
        queryResult.close()
        return userExists
    }

    fun getUser(handler: Handler): User {
        val getUser = connection.prepareStatement("SELECT hash, salt FROM users WHERE login = ?")
        getUser.setString(1, handler.login)
        val result = getUser.executeQuery()
        result.next()
        val hash = result.getString("hash")
        val salt = result.getString("salt")
        result.close()
        return User(handler.login!!, hash, salt)
    }

    fun getUserAccessInfo(handler: Handler): MutableList<String> {
        var hasAccess = false
        val searchRights = connection.prepareStatement("SELECT resource_name FROM resources WHERE login = ? AND role = ?")
        searchRights.setString(1, handler.login)
        searchRights.setString(2, handler.role)
        val result = searchRights.executeQuery()
        val mutableList = mutableListOf<String>()
        while (result.next())
            mutableList.add(result.getString("resource_name"))
        result.close()
        return mutableList
    }

    fun addSession(handler: Handler) {
        val dataSession = connection.prepareStatement("INSERT INTO sessions (login, role, resources, date_start, date_end, data_size) VALUES (?, ?, ?, ?, ?, ?)")
        dataSession.setString(1, handler.login)
        dataSession.setString(2, handler.role)
        dataSession.setString(3, handler.res)
        dataSession.setString(4, handler.ds)
        dataSession.setString(5, handler.de)
        dataSession.setString(6, handler.vol)
        dataSession.close()
    }

    fun closeConnection() = connection.close()
}