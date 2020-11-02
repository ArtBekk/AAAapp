package models

class Resource(input: String) {

    private val name: List<String> = input.split('.')

    fun contains(input: String): Boolean {
        var result = true
        val splitInput = input.split('.')
        for (i in name.indices) {
            try {
                if (splitInput[i] != name[i]) result = false
            } catch (e: IndexOutOfBoundsException) {
                result = false
            }
        }
        return result
    }
}
