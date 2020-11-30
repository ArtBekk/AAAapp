import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
import org.mockito.Mock
import services.*
import java.sql.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.junit.jupiter.api.Assertions

class App(args: Array<String>) {

    private val logger: Logger = LogManager.getLogger()

    private val handler: Handler = if (args.isNullOrEmpty())
        Handler(arrayOf("-h"))
    else
        Handler(args)

    @Mock
    private var dal: DataAccessLayer = DataAccessLayer(connectToDB())

    private var result: ExitCode = ExitCode.Success

    private fun connectToDB(): Connection {
        val flyway = Flyway.configure().dataSource("jdbc:h2:file:./db/aaa" + ";MV_STORE=FALSE",
                "ArtBekk",
                "3678").locations("filesystem:db/migration").load()
        flyway.migrate()

        return DriverManager.getConnection("jdbc:h2:file:./db/aaa" + ";MV_STORE=FALSE",
                "ArtBekk",
                "3678")
    }

    fun run(): Int {
        if (handler.authenticationNeeded()) {
            logger.info("STEP 1: Authentication")
            @Mock
            result = authenticate(handler, dal)
            logger.info("Authentication status: $result")
        }
        if (handler.authorizationNeeded() && result == ExitCode.Success) {
            logger.info("STEP 2: Authorization")
            @Mock
            result = authorize(handler, dal)
            logger.info("Authorization status: $result")
        }
        if (handler.accountingNeeded() && result == ExitCode.Success) {
            logger.info("STEP 3: Accounting.")
            @Mock
            result = account(handler, dal)
            logger.info("Accounting status: $result")
        }
        dal.closeConnection()
        return result.value
    }
}

class AppTest : Spek({
    given("App class") {
        val sampleApp = App(arrayOf("-login", "ArtBekk", "-pass", "3678", "-role", "WOLOLOLO",
                "-res", "AV"))
        on("launch") {
            val result = sampleApp.run()
            it("doesn't account") {
                Assertions.assertEquals(result, ExitCode.UnknownRole.value)
            }
        }


    }
    given("") {
        val sampleApp = App(arrayOf("-login", "ArtBekk", "-pass", "3678"))
        on("launch") {
            val result = sampleApp.run()
            it("only authenticates") {
                Assertions.assertEquals(result, ExitCode.Success.value)
            }
        }
    }
})