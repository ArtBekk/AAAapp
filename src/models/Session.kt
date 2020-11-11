package models

import java.util.Date

class Session(val user: String, val res: String, val role: Roles, val dateStart: Date, val dateEnd: Date, val dataSize: Int)
