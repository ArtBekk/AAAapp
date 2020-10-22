package models

class User(val login: String, val password: Int, val write: Array<Resource>?, val read: Array<Resource>?, val execute: Array<Resource>?)