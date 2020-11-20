import models.User
import java.sql.Connection

class DataAccessLayer(private val connection: Connection) {

    fun userExists(handler: Handler): Boolean {
        var userExists = false
        logger.info("Preparing and sending an SQL query.")
        val searchUser = connection.prepareStatement("SELECT* FROM users WHERE login = ?")
        logger.info("Getting data from a database.")
        searchUser.setString(1, handler.login)
        val queryResult = searchUser.executeQuery()
        logger.info("The data was successfully received.")
        logger.info("Search for the right user.")
        if (queryResult.next()) userExists = true
        queryResult.close()
        return userExists
    }

    fun getUser(handler: Handler): User {
        logger.info("Preparing and sending an SQL query.")
        val getUser = connection.prepareStatement("SELECT hash, salt FROM users WHERE login = ?")
        getUser.setString(1, handler.login)
        logger.info("Getting data from a database.")
        val result = getUser.executeQuery()
        result.next()
        val hash = result.getString("hash")
        val salt = result.getString("salt")
        result.close()
        logger.info("The data was successfully received.")
        return User(handler.login!!, hash, salt)
    }

    fun getUserAccessInfo(handler: Handler): MutableList<String> {
        logger.info("Preparing and sending an SQL query.")
        val searchRights = connection.prepareStatement("SELECT resource_name FROM resources WHERE login = ? AND role = ?")
        searchRights.setString(1, handler.login)
        searchRights.setString(2, handler.role)
        logger.info("Getting data from a database.")
        val result = searchRights.executeQuery()
        val mutableList = mutableListOf<String>()
        while (result.next())
            mutableList.add(result.getString("resource_name"))
        result.close()
        logger.info("The data was successfully received.")
        return mutableList
    }

    fun addSession(handler: Handler) {
        logger.info("Preparing and sending an SQL query.")
        val dataSession = connection.prepareStatement("INSERT INTO sessions (login, role, resources, date_start, date_end, data_size) VALUES (?, ?, ?, ?, ?, ?)")
        dataSession.setString(1, handler.login)
        dataSession.setString(2, handler.role)
        dataSession.setString(3, handler.res)
        dataSession.setString(4, handler.ds)
        dataSession.setString(5, handler.de)
        dataSession.setString(6, handler.vol)
        dataSession.close()
        logger.info("The recording session is successfully created.")
    }

    fun closeConnection() = connection.close()
}