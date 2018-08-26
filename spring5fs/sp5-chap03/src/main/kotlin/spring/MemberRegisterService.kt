package spring

import java.time.LocalDateTime

class MemberRegisterService(private var memberDao: MemberDao) {

    fun regist(req: RegisterRequest): Long? {
        val member: Member? = memberDao.selectByEmail(req.email)
        if (member != null) {
            throw DuplicatedMemberException("dup email $req.email")
        }
        val newMember = Member(email = req.email, password = req.password, name = req.name, registerDateTime = LocalDateTime.now())
        memberDao.insert(newMember)
        return newMember.getId()
    }

    fun getMemberList(): Map<String, Member> {
        return this.memberDao.getAll()
    }
}