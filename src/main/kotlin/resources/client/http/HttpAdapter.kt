package resources.client.http

import resources.client.Response

interface HttpAdapter {
    fun get(path: String): Response
}
