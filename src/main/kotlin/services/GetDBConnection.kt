package services

import org.apache.logging.log4j.LogManager
import org.flywaydb.core.Flyway
import java.sql.Connection
import java.sql.DriverManager

fun getDBConnection(url: String, login: String, pass: String): Connection {
    val logger = LogManager.getLogger()
    logger.info("Launching a migration")

    val flyway = Flyway.configure().dataSource("$url;MV_STORE=FALSE", login, pass).locations("filesystem:db/migration").load()

    flyway.migrate()
    logger.info("Migration successful")

    return DriverManager.getConnection("$url;MV_STORE=FALSE", login, pass)
}