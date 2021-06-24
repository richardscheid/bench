package resources.client

data class Response(
    val statusCode: Int,
    val body: String
)

val Response.isSuccessful get() = (statusCode / 100) == 2

val Response.isClientError get() = (statusCode / 100) == 4

val Response.isServerError get() = (statusCode / 100) == 5
