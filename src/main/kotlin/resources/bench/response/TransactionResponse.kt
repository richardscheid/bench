package resources.bench.response

import domain.transaction.entities.Transaction
import kotlin.math.ceil

data class TransactionResponse(
    val totalCount: Int,
    val page: Int,
    val transactions: MutableList<Transaction>
) {
    fun toTransaction() = Pair(this.transactions, calculatePages())

    fun calculatePages() = ceil(totalCount.div(transactions.size.toDouble())).toInt()
}
