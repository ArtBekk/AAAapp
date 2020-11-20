import org.flywaydb.core.Flyway
import services.*
import java.io.File
import java.sql.*

class App(args: Array<String>) {

    private val handler: Handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)

    private var dal: DataAccessLayer = DataAccessLayer(connectToDB())

    private var result: ExitCode = ExitCode.Success

    private fun connectToDB() : Connection {
        if (!File("./db", "aaa.h2.db").exists()) {
            logger.info("No database file is present, creating database")
            val flyway = Flyway.configure().dataSource(System.getenv("URL") + ";MV_STORE=FALSE",
                    System.getenv("LOGIN"),
                    System.getenv("PASS")).locations("filesystem:db/migration").load()
            flyway.migrate()
        }
        return DriverManager.getConnection(System.getenv("URL") + ";MV_STORE=FALSE",
                System.getenv("LOGIN"),
                System.getenv("PASS"))
    }

    fun run(): Int {
        if (handler.authenticationNeeded()) {
            logger.info("STEP 1: Authentication")
            result = authenticate(handler, dal)
            logger.info("Authentication status: $result")
        }
        if (handler.authorizationNeeded() && result == ExitCode.Success) {
            logger.info("STEP 2: Authorization")
            result = authorize(handler, dal)
            logger.info("Authorization status: $result")
        }
        if (handler.accountingNeeded() && result == ExitCode.Success) {
            logger.info("STEP 3: Accounting.")
            result = account(handler, dal)
            logger.info("Accounting status: $result")
        }
        dal.closeConnection()
        return result.value
    }
}
