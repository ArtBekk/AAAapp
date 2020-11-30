package models

class User(val login: String, val hash: String, val salt: String)
