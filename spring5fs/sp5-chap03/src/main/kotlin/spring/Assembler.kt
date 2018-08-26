package spring

class Assembler {
    private val memberDao: MemberDao = MemberDao()
    private val regSvc: MemberRegisterService = MemberRegisterService(this.memberDao)
    private val pwdSvc: ChangePasswordService = ChangePasswordService(this.memberDao)

    fun getRegSvc(): MemberRegisterService {
        return this.regSvc
    }

    fun getPwdSvc(): ChangePasswordService {
        return this.pwdSvc
    }
}