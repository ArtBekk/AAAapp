package services

import ExitCode
import Handler
import domains.sessions
import models.Session
import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException
import java.util.*

fun account(handler: Handler): ExitCode{

    val fDate = SimpleDateFormat ("yyyy-MM-dd")

    var dateS: Date
    var dateE: Date
    var dataSize: Int

    try{
        dateS = fDate.parse(handler.ds)
        dateE = fDate.parse(handler.de)
    }
    catch (exc: DateTimeParseException){
        return ExitCode.INCORRECTACTIVITY
    }

    try {
        dataSize = handler.vol.toInt()
    }
    catch (exc: NumberFormatException){
        return ExitCode.INCORRECTACTIVITY
    }

    val newSession = Session(handler.login, dateS, dateE, dataSize)
    sessions.add(newSession)

    return ExitCode.SUCCESS
}