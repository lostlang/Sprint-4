import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest
    @MethodSource("success save data set")
    fun `success save client`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest
    @MethodSource("fail save client data set")
    fun `fail save client`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        assertThrows<ValidationException>() {
            clientService.saveClient(client)
        }
    }

    companion object {
        @JvmStatic
        fun `success save data set`() = listOf(
            Arguments.of("/success/user.json"),
            Arguments.of("/success/user2.json"),
        )

        @JvmStatic
        fun `fail save client data set`() = listOf(
            Arguments.of("/fail/user_data_corrupted.json"),
            Arguments.of("/fail/user_snils_corrupted.json"),
            Arguments.of("/fail/user_with_bad_email.json"),
            Arguments.of("/fail/user_with_bad_phone.json"),
            Arguments.of("/fail/user_with_bad_snils.json"),
        )
    }

    @Test
    fun `Validation error - test1`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")

        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LONG)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
    }

    @Test
    fun `Validation error - test2`() {
        val client = getClientFromJson("/fail/user_snils_corrupted.json")

        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LONG)
    }

    @Test
    fun `Validation error - test3`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")

        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_EMAIL)
    }

    @Test
    fun `Validation error - test4`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")

        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE)
    }

    @Test
    fun `Validation error - test5`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")

        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}