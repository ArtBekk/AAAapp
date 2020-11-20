package services

import DataAccessLayer
import ExitCode
import Handler
import logger
import java.text.SimpleDateFormat

fun account(handler: Handler, dal: DataAccessLayer): ExitCode {

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
            dal.addSession(handler)
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
