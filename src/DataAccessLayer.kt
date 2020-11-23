import models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.sql.Connection


class DataAccessLayer(private val connection: Connection) {

    val logger: Logger = LogManager.getLogger()

    fun userExists(login: String): Boolean {
        var userExists = false

        logger.info("Preparing and sending an SQL query.")
        connection.prepareStatement("SELECT* FROM users WHERE login = ?").use {

            logger.info("Getting data from a database.")
            it.setString(1, login)
            val queryResult = it.executeQuery()

            logger.info("The data was successfully received.")
            logger.info("Search for the right user.")
            if (queryResult.next()) userExists = true
        }

        return userExists
    }

    fun getUser(login: String): User {
        logger.info("Preparing and sending an SQL query.")
        connection.prepareStatement("SELECT hash, salt FROM users WHERE login = ?").use {
            it.setString(1, login)

            logger.info("Getting data from a database.")
            val result = it.executeQuery()
            result.next()
            val hash = result.getString("hash")
            val salt = result.getString("salt")

            logger.info("The data was successfully received.")
            return User(login, hash, salt)
        }
    }

    fun getUserAccessInfo(login: String, role: String): MutableList<String> {
        logger.info("Preparing and sending an SQL query.")
        connection.prepareStatement("SELECT resource_name FROM resources WHERE login = ? AND role = ?").use {
            it.setString(1, login)
            it.setString(2, role)


            logger.info("Getting data from a database.")
            val result = it.executeQuery()

            val mutableList = mutableListOf<String>()
            while (result.next())
                mutableList.add(result.getString("resource_name"))


            logger.info("The data was successfully received.")
            return mutableList
        }
    }

    fun addSession(login: String, role: String, res: String, ds: String, de: String, vol: String) {
        logger.info("Preparing and sending an SQL query.")
        connection.prepareStatement("INSERT INTO sessions (login, role, resources, date_start, date_end, data_size) VALUES (?, ?, ?, ?, ?, ?)").use {

            it.setString(1, login)
            it.setString(2, role)
            it.setString(3, res)
            it.setString(4, ds)
            it.setString(5, de)
            it.setString(6, vol)

            it.execute()
            logger.info("The recording session is successfully created.")
        }
    }

    fun closeConnection() = connection.close()
}
