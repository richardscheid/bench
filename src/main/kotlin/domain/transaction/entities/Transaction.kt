package domain.transaction.entities

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

data class Transaction(
    @JsonProperty(value = "Company") val company: String,
    @JsonProperty(value = "Ledger") val ledger: String,
    @JsonProperty(value = "Amount") val amount: Double,
    @JsonProperty(value = "Date") val date: DateTime
)
