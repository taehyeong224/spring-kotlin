package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spring.ChangePasswordService
import spring.MemberDao
import spring.MemberRegisterService

@Configuration
class AppCtx {

    @Bean
    fun memberDao() = MemberDao()

    @Bean
    fun memberRegSvc() = MemberRegisterService(memberDao())

    @Bean
    fun changePwdSvc() = ChangePasswordService(memberDao())
}