package domain.transaction

import domain.transaction.entities.Transaction

interface TransactionGateway {
    fun getTransactions(page: Int): Pair<MutableList<Transaction>, Int>
}
