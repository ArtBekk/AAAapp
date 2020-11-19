package models

class Access(private val resource: String, val login: String, val role: Roles) {
    /**
     * Function splits given string in different parts and compares those with parts of resource's name. Delimiter is '.'
     */
    fun isResSubsidiary(res: String): Boolean {
        val name: List<String> = resource.split('.')
        var result = true
        val splitInput = res.split('.')
        for (i in name.indices) {
            if (i < splitInput.size) {
                if (splitInput[i] != name[i]) result = false
            } else break
        }
        return result
    }
}
