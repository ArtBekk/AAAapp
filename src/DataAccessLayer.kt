import models.User
import org.h2.jdbcx.JdbcConnectionPool

class DataAccessLayer {

    private val cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa")


    fun userExists(handler: Handler): Boolean {
        val con = cp.getConnection("sa", "sa")
        var userExists = false

        val searchUser = con.prepareStatement("SELECT login FROM users Where login = ?")
        searchUser.setString(1, handler.login)
        if (searchUser.executeQuery().next()) userExists = true
        con.close()
        return userExists
    }

    fun getUser(handler: Handler): User {
        val con = cp.getConnection("sa", "sa")
        val getUser = con.prepareStatement("SELECT hash, salt FROM users Where login = ?")
        getUser.setString(1, handler.login)
        val result = getUser.executeQuery()
        result.next()
        val hash = result.getString("hash")
        val salt = result.getString("salt")
        con.close()

        return User(handler.login!!, hash, salt)
    }

    fun getUserAccess(handler: Handler): Boolean {
        val con = cp.getConnection("sa", "sa")
        var hasAccess = false
        val searchRights = con.prepareStatement("SELECT login, Role, ResourceName " +
                "FROM resources Where login = ? and Role = ?")
        searchRights.setString(1, handler.login)
        searchRights.setString(2, handler.res)
        searchRights.setString(3, handler.role)
        val result = searchRights.executeQuery()
        if(result.next()) hasAccess = true
        con.close()
        return hasAccess
    }

    fun addSession(handler: Handler) {
        val con = cp.getConnection("sa", "sa")
        val dataSession = con.prepareStatement("INSERT INTO sessions (login, role, resources," +
                " dateStart, dateEnd, dataSize) VALUES (?, ?, ?, ?, ?, ?);")
        dataSession.setString(1, handler.login)
        dataSession.setString(2, handler.role)
        dataSession.setString(3, handler.res)
        dataSession.setString(4, handler.ds)
        dataSession.setString(5, handler.de)
        dataSession.setString(6, handler.vol)
        con.close()
    }
}