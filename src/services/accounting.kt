package services

import ExitCode
import Handler
import domains.sessions
import models.Session
import java.text.SimpleDateFormat

fun account(handler: Handler): ExitCode{

    val fDate = SimpleDateFormat ("yyyy-MM-dd")
    val dataSize: Int
    var result: ExitCode

    try{
       val dateS = fDate.parse(handler.ds)
        val dateE = fDate.parse(handler.de)

        dataSize = handler.vol!!.toInt()

        result = if(dataSize in 0..1000) {
            val newSession = Session(handler.login!!, dateS, dateE, dataSize)
            sessions.add(newSession)
            ExitCode.SUCCESS
        }
        else ExitCode.INCORRECTACTIVITY
    }
    catch ( exc: Exception){
        result = ExitCode.INCORRECTACTIVITY
    }
    return result
}