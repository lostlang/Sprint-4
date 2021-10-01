abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}


class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val reg = "[78][0-9]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(reg))
            return listOf(ErrorCode.INVALID_PHONE)

        return listOf()
    }
}


class DataValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val reg = "[аА-яЯ]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > 16)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(reg))
            return listOf(ErrorCode.INVALID_DATA)

        return listOf()
    }
}


class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val reg = "^[aA-zZ0-9]+@[aA-zZ]+\\.[aA-zZ]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > 32)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(reg))
            return listOf(ErrorCode.INVALID_EMAIL)

        return listOf()
    }
}


class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[0-9]+".toRegex()

        if (value == null)
            return listOf(ErrorCode.INVALID_EMAIL)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_SNILS)

        val snils = stringToIntArray(value.dropLast(2))
        val controlSum = value.takeLast(2).toInt()

        if (calculateControlSum(snils) != controlSum)
            return listOf(ErrorCode.INVALID_SNILS)

        return listOf()
    }

    private fun calculateControlSum(snils: IntArray): Int {
        var controlSum = 0
        var index = 9

        for (digit in snils) {
            controlSum += digit * index
            index--
        }

        while (controlSum > 101) {
            controlSum %= 101
        }

        if (controlSum == 100 || controlSum == 101) {
            controlSum = 0
        }

        return controlSum
    }

    private fun stringToIntArray(string: String): IntArray {
        return string.map { it.toString().toInt() }.toIntArray()
    }
}