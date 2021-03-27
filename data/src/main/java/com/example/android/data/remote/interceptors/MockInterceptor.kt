package com.example.android.data.remote.interceptors

import android.app.Application
import android.util.Log
import com.example.android.data.R
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.BufferedReader
import java.io.InputStreamReader

class MockInterceptor(private val application: Application) : Interceptor {
    private val responseCodeError = 408
    private val responseSessionError = 401
    private val responseCodeOK = 200
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl= chain.request().url.toString()
        return when {
            requestUrl.contains("limit") -> {
                getMockResponse("limit", R.raw.pokemons, chain, responseCodeOK)!!
            }
            requestUrl.contains("pokemon/") -> {
                getMockResponse("pokemon/", R.raw.pokemon, chain, responseCodeOK)!!
            }
            else -> {
                chain.proceed(chain.request())
            }
        }
    }

    private fun getMockResponse(
        urlContains: String,
        jsonResource: Int,
        chain: Interceptor.Chain,
        responseCode: Int
    ): Response? {
        return getMockResponse(null, urlContains, jsonResource, chain, responseCode)
    }

    private fun getMockResponse(
        method: String?,
        urlContains: String,
        jsonResource: Int,
        chain: Interceptor.Chain,
        responseCode: Int
    ): Response? {
        if (method != null && !method.equals(chain.request().method, true)) {
            return null
        }
        val strUrl =chain.request().url.toString()
        var contains = true
        val urls = urlContains.split("%")
        for (x in urls) {
            contains = contains && strUrl.contains(x)
        }
        if (contains) {
            return returnMockResponse(chain, responseCode, jsonResource, application)
        }
        return null
    }

    private fun returnMockResponse(
        chain: Interceptor.Chain,
        responseCode: Int,
        responseString: String
    ): Response {
        return Response.Builder()
            .protocol(Protocol.HTTP_1_0)
            .code(responseCode)
            .request(chain.request())
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun returnMockResponse(
        chain: Interceptor.Chain,
        responseCode: Int,
        resource: Int,
        app: Application
    ): Response? {
        val responseString = getStringFromResource(resource, app)
        return returnMockResponse(chain, responseCode, responseString)
    }

    private fun getStringFromResource(resource: Int, app: Application): String {
        val inputStream = app.resources.openRawResource(resource)
        val br = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?
        line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close()
        return sb.toString()
    }
}