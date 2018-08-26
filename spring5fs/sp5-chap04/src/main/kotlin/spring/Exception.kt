package spring

class WrongIdPasswordException : RuntimeException()
class DuplicatedMemberException(message: String) : RuntimeException(message)
class MemberNotFoundException : RuntimeException()