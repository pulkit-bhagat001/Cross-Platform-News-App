package com.app.news.data.network

import io.ktor.client.HttpClient

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse


class Network(
    val httpClient: HttpClient
) {


   suspend fun getHeadlines(category:String):HttpResponse{
        return httpClient.get {
            url("top-headlines")
            parameter("category", category)
          parameter("apiKey", "insertYourApiKey")
        }
    }
    suspend fun getBySearch(query:String):HttpResponse{
        return httpClient.get {
            url("everything")
            parameter("q", query)
            parameter("apiKey", "insertYourApiKey")
        }
    }
}