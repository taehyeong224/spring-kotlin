package spring

import java.time.LocalDateTime


data class Member(val email: String, var password: String, val name: String, val registerDateTime: LocalDateTime) {
    private val id: Long? = null

    fun getId(): Long? {
        return this.id
    }

    fun changePassword(oldPassword: String, newPassword: String) {
        if (password != oldPassword) throw WrongIdPasswordException()
        password = newPassword
    }
}
