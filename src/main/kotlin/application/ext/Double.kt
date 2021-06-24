package application.ext

fun Double.format(digits: Int) = "%.${digits}f".format(this)
