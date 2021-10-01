class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE(101, "Не корректный номер телефона"),
    INVALID_DATA(102, "Имя и фамилия должны состоять только из кириллици"),
    INVALID_EMAIL(103, "Почта должна быть написана на латинице"),
    INVALID_SNILS(104, "Снилс должен состоять только из цифр"),
    NULL_VALUE(105, "Значение равно null"),
    INVALID_LONG(106, "Недопустимая длина значения")
}