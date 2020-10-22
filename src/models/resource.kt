package models

class Resource(input: String) {

    private val name: List<String> = input.split('.')

    fun contains(input: String): Boolean {
        var result: Boolean = true
        val splitInput = input.split('.')
        for (i in 1..name.size) {
            if (splitInput[i] != name[i]) result = false
        }
        return result
    }
}