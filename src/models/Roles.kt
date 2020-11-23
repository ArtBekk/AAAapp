package models

enum class Roles() {
    READ,
    WRITE,
    EXECUTE;

    companion object {
        fun contains(name: String): Boolean = enumValues<Roles>().any { it.name == name }
    }
}
