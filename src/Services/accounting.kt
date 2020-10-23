package Services

import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException
import Handler
import ExitCode
import models.Session
import sessions
import java.util.*

fun session(handler: Handler): ExitCode{

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