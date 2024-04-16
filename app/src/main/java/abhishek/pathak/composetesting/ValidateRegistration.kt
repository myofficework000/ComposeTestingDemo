package abhishek.pathak.composetesting

object ValidateRegistration {

    private val existingUsers = listOf("Alex","Ethan","Jeremy","Ryan")

    fun isValidRegistrationInput(
        username : String,
        password : String,
        confirmPassword: String
    ) : Boolean {
        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) return false
        if (username in existingUsers) return false
        if (password != confirmPassword) return false
        if (password.count { it.isDigit()} < 6) return false
        return true
    }
}