package models

class Resource(input: String) {

    private val name: List<String> = input.split('.')

    /*
    * Function splits given string in different parts and compares those with parts of resource's name. Delimiter is '.'
    * */
    fun contains(input: String): Boolean {
        var result = true
        val splitInput = input.split('.')
        for (i in name.indices) {
            if (i < splitInput.size) {
                if (splitInput[i] != name[i]) result = false
            } else break
        }
        return result
    }
}
