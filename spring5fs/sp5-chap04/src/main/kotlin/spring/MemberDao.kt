package spring

import kotlin.collections.HashMap

class MemberDao {
    private val map = HashMap<String, Member>()

    companion object {
        var nextId: Long = 0
    }

    fun selectByEmail(email: String): Member? {
        return map[email]
    }

    fun insert(member: Member) {
        ++MemberDao.nextId
        println("nexId = ${MemberDao.nextId}")
        map.put(member.email, member)
    }

    fun update(member: Member) {
        map.put(member.email, member)
    }

    fun getAll(): HashMap<String, Member> {
        return map
    }
}