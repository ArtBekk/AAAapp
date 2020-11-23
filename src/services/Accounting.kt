package services

import DataAccessLayer
import ExitCode
import Handler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.text.SimpleDateFormat

fun account(handler: Handler, dal: DataAccessLayer): ExitCode {

    val logger: Logger = LogManager.getLogger()

    val fDate = SimpleDateFormat("yyyy-MM-dd")
    val dataSize: Int
    var result: ExitCode

    try {
        logger.info("Checking the validity of a date.")
        val dateS = fDate.parse(handler.ds)
        val dateE = fDate.parse(handler.de)
        logger.info("The date format is correct.")

        dataSize = handler.vol!!.toInt()

        logger.info("Checking the validity of the volume.")
        result = if (dataSize in 0..1000) {
            logger.info("Valid volume.")
            logger.info("Request to record the current session.")
            dal.addSession(handler.login!!, handler.role!!, handler.res!!, handler.ds!!,
                    handler.ds!!, handler.vol!!)
            ExitCode.Success
        } else {
            logger.info("The volume is invalid.")
            ExitCode.IncorrectActivity
        }
    } catch (exc: Exception) {
        logger.info("Incorrect activity. Invalid date.")
        result = ExitCode.IncorrectActivity
    }
    return result
}
