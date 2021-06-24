package application.ext

import org.joda.time.DateTime

fun DateTime.format(): String = this.toString("yyyy-MM-dd")

fun DateTime.timeZero(): DateTime = DateTime(this.year, this.monthOfYear, this.dayOfMonth, 0, 0, 0)
