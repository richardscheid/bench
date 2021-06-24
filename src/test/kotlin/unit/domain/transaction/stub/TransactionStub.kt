package unit.domain.transaction.stub

import application.ext.timeZero
import domain.transaction.entities.Transaction
import org.joda.time.DateTime

const val PAGE = 1

val TRANSACTION_STUB = Transaction(
    company = "Bench",
    ledger = "Account",
    date = DateTime.now().timeZero(),
    amount = 10.0
)

val TRANSACTION_RESPONSE_STUB = Pair(
    mutableListOf(
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB
    ),
    PAGE
)

val TRANSACTION_RESPONSE_MULTIPLE_DATES_STUB = Pair(
    mutableListOf(
        TRANSACTION_STUB.copy(date = DateTime.now().timeZero()),
        TRANSACTION_STUB.copy(date = DateTime.now().timeZero()),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(1).timeZero(), amount = -37.50),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(1).timeZero(), amount = -52.75),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(2).timeZero(), amount = -10.15),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(2).timeZero(), amount = -37.50),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(3).timeZero(), amount = 17.50),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(3).timeZero(), amount = -42.10),
        TRANSACTION_STUB.copy(date = DateTime.now().minusDays(4).timeZero(), amount = 88.25),
        TRANSACTION_STUB.copy(date = DateTime.now().timeZero(), amount = -75.00),
    ),
    PAGE
)
