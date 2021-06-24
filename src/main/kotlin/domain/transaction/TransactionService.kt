package domain.transaction

import application.ext.format
import domain.transaction.entities.Transaction
import org.joda.time.DateTime

class TransactionService(private val gateway: TransactionGateway) {

    fun calculateDailyBalance() {
        val transactions = findAllTransactions()

        val balances: Map<DateTime, Double> = sumBalanceByDate(transactions)

        calculateBalanceSummary(balances)
    }

    fun calculateBalanceSummary(balances: Map<DateTime, Double>) {
        var total = 0.0
        balances.forEach { (date, balance) ->
            run {
                total += balance
                println("${date.format()} ${total.format(digits = 2)}")
            }
        }
    }

    fun sumBalanceByDate(transactions: List<Transaction>): Map<DateTime, Double> {
        return transactions
            .groupingBy { it.date } // group all transactions by date
            .fold(initialValue = 0.0) { sum, transaction -> sum + transaction.amount } // sum all grouped balances
            .toSortedMap() // order by asc
    }

    fun findAllTransactions(): List<Transaction> {
        var page = 1
        val (transactions, pages) = gateway.getTransactions(page++) // get transactions from first page

        while (page <= pages) { // go until the last page
            transactions.addAll(gateway.getTransactions(page++).first)
        }

        return transactions
    }
}
