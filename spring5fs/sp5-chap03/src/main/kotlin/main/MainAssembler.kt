package main

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import spring.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainAssembler {
    private companion object {
        val assembler = Assembler()
        lateinit var ctx: ApplicationContext
        lateinit var memberDao: MemberDao

        @JvmStatic
        fun main(args: Array<String>) {
            ctx = AnnotationConfigApplicationContext(AppCtx().javaClass)
            this.memberDao = ctx.getBean("memberDao", MemberDao().javaClass)
            val reader = BufferedReader(InputStreamReader(System.`in`))
            while (true) {
                println("명령어를 입력하세요")
                val command = reader.readLine()
                if (command.equals("exit", true)) {
                    println("종료합니다")
                    break
                }

                if (command.startsWith("new")) {
                    processNewCommand(command.split(" "))
                    continue
                } else if (command.startsWith("change")) {
                    processChangeCommand(command.split(" "))
                    continue
                }
                printHelp()
            }
        }

        fun processNewCommand(arg: List<String>) {
            if (arg.size != 5) {
                printHelp()
                return
            }
            val regSvc = ctx.getBean("memberRegSvc", MemberRegisterService(this.memberDao).javaClass)
            val req = RegisterRequest(email = arg[1], name = arg[2], password = arg[3], confirmPassword = arg[4])

            if (!req.isPasswordEqualToConfirmPassword()) {
                println("암호와 확인이 일치하지 않습니다.")
                return
            }

            try {
                regSvc.regist(req)
                println("등록했습니다.")
                println(regSvc.getMemberList())
            } catch (e: DuplicatedMemberException) {
                println("이미 존재하는 이메일 입니다.")
            }
        }

        fun processChangeCommand(arg: List<String>) {
            if (arg.size != 4) {
                printHelp()
                return
            }
            val changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService(this.memberDao).javaClass)
            try {
                changePwdSvc.changePassword(arg[1], arg[2], arg[3])
                println("성공적으로 변경하였습니다.")
            } catch (e: MemberNotFoundException) {
                println("존재하지 않는 이메일 입니다.")
            } catch (e: WrongIdPasswordException) {
                println("이메일과 암호가 일치하지 않습니다.")
            }
        }

        fun printHelp() {
            println()
            println("잘못된 명령어 입니다.")
        }
    }
}