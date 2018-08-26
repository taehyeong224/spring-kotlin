package spring


data class RegisterRequest(val email: String, val password: String, val confirmPassword: String, val name: String) {
    fun isPasswordEqualToConfirmPassword(): Boolean {
        return password == confirmPassword
    }
}