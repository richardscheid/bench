package unit.domain.transaction

import application.ext.timeZero
import domain.transaction.TransactionGateway
import domain.transaction.TransactionService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import unit.domain.transaction.stub.TRANSACTION_RESPONSE_MULTIPLE_DATES_STUB
import unit.domain.transaction.stub.TRANSACTION_RESPONSE_STUB
import unit.domain.transaction.stub.TRANSACTION_STUB

class TransactionServiceTest {

    private val gateway = mockk<TransactionGateway>()
    private val service = TransactionService(gateway)

    @Test
    fun `findAllTransactions() should find all transactions on the first page successfully`() {
        val pages = 1
        val expectedSize = 3
        val transactions = TRANSACTION_RESPONSE_STUB

        every { gateway.getTransactions(pages) } returns transactions

        val response = service.findAllTransactions()

        verify(exactly = 1) { gateway.getTransactions(pages) }

        assertEquals(response.size, expectedSize)
        assertEquals(response[0].company, transactions.first[0].company)
        assertEquals(response[0].ledger, transactions.first[0].ledger)
        assertEquals(response[0].amount, transactions.first[0].amount)
        assertEquals(response[0].date, transactions.first[0].date)
    }

    @Test
    fun `findAllTransactions() should find all transactions from the three pages successfully`() {
        val pages = 3
        val expectedSize = 12
        val transactions = TRANSACTION_RESPONSE_STUB.copy(second = pages)

        every { gateway.getTransactions(any()) } returns transactions

        val response = service.findAllTransactions()

        verify(exactly = 3) { gateway.getTransactions(any()) }

        assertEquals(response.size, expectedSize)
    }

    @Test
    fun `sumBalanceByDate() should sum all balances grouped by current date successfully`() {
        val expectedSize = 1
        val expectedBalance = 30.0
        val transactions = TRANSACTION_RESPONSE_STUB
        val transaction = TRANSACTION_STUB

        val response = service.sumBalanceByDate(transactions.first)

        assertEquals(response.entries.size, expectedSize)
        assertEquals(response[transaction.date], expectedBalance)
    }

    @Test
    fun `sumBalanceByDate() should sum all balances grouped by different dates successfully`() {
        val expectedSize = 5
        val transactions = TRANSACTION_RESPONSE_MULTIPLE_DATES_STUB

        val response = service.sumBalanceByDate(transactions.first)

        assertEquals(response.entries.size, expectedSize)
        assertEquals(response[DateTime.now().timeZero()], -55.00)
        assertEquals(response[DateTime.now().minusDays(1).timeZero()], -90.25)
        assertEquals(response[DateTime.now().minusDays(2).timeZero()], -47.65)
        assertEquals(response[DateTime.now().minusDays(3).timeZero()], -24.60)
        assertEquals(response[DateTime.now().minusDays(4).timeZero()], 88.25)
    }
}
