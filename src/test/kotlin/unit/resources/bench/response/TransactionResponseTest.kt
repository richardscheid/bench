package unit.resources.bench.response

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import unit.resources.bench.stub.TRANSACTION_RESPONSE_STUB

class TransactionResponseTest {

    @Test
    fun `calculatePages() should calculate four pages successfully`() {
        val transactionResponse = TRANSACTION_RESPONSE_STUB
        val expectedPages = 4

        assertEquals(transactionResponse.calculatePages(), expectedPages)
    }

    @Test
    fun `calculatePages() should calculate two pages successfully`() {
        val transactionResponse = TRANSACTION_RESPONSE_STUB.copy(totalCount = 11)
        val expectedPages = 2

        assertEquals(transactionResponse.calculatePages(), expectedPages)
    }

    @Test
    fun `calculatePages() should calculate one page successfully`() {
        val transactionResponse = TRANSACTION_RESPONSE_STUB.copy(totalCount = 9)
        val expectedPages = 1

        assertEquals(transactionResponse.calculatePages(), expectedPages)
    }
}
