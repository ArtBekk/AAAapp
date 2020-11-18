import org.h2.jdbcx.JdbcConnectionPool

class DataAccessLayer {

    private val cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa")


    fun userSearch() {
        val con = cp.getConnection("sa", "sa")

        con.close()

    }

    fun passwordPrompt() {
        val con = cp.getConnection("sa", "sa")

        con.close()
    }

    fun userRightsVerification() {
        val con = cp.getConnection("sa", "sa")

        con.close()

    }

    fun addSession() {
        val con = cp.getConnection("sa", "sa")

        con.close()

    }
}