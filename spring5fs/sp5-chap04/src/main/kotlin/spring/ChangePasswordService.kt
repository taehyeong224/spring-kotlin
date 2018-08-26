package spring

class ChangePasswordService(private var memberDao: MemberDao) {
    fun changePassword(email: String, oldPwd: String, newPwd: String) {
        val member = memberDao.selectByEmail(email) ?: throw MemberNotFoundException()
        member.changePassword(oldPwd, newPwd)
        memberDao.update(member)
    }
}