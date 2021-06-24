package unit.resources.bench.stub

import org.eclipse.jetty.http.HttpStatus
import resources.client.Response

val NOT_FOUND_RESPONSE_STUB = Response(
    statusCode = HttpStatus.NOT_FOUND_404,
    body = "NOT_FOUND_ERROR"
)

val OK_RESPONSE_STUB = Response(
    statusCode = HttpStatus.OK_200,
    body = "SUCCESS_BODY"
)
