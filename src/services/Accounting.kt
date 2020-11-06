package services

import ExitCode
import Handler
import domains.sessions
import models.Session
import java.text.SimpleDateFormat

fun account(handler: Handler): ExitCode {

    val fDate = SimpleDateFormat("yyyy-MM-dd")
    val dataSize: Int
    var result: ExitCode

    try {
        val dateS = fDate.parse(handler.ds)
        val dateE = fDate.parse(handler.de)

        dataSize = handler.vol!!.toInt()

        result = if (dataSize in 0..1000) {
            sessions.add(Session(user = handler.login!!, res = handler.res!!, role = handler.role!!, dateS, dateE, dataSize))
            ExitCode.Success
        } else ExitCode.IncorrectActivity
    } catch (exc: Exception) {
        result = ExitCode.IncorrectActivity
    }
    return result
}
