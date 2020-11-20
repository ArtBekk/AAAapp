package services

import DataAccessLayer
import ExitCode
import Handler
import java.text.SimpleDateFormat

fun account(handler: Handler, dal: DataAccessLayer): ExitCode {

    val fDate = SimpleDateFormat("yyyy-MM-dd")
    val dataSize: Int
    var result: ExitCode

    try {
        val dateS = fDate.parse(handler.ds)
        val dateE = fDate.parse(handler.de)

        dataSize = handler.vol!!.toInt()

        result = if (dataSize in 0..1000) {
            dal.addSession(handler)
            ExitCode.Success
        } else ExitCode.IncorrectActivity
    } catch (exc: Exception) {
        result = ExitCode.IncorrectActivity
    }
    return result
}
