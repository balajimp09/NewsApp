package com.sur.newsapp

import java.io.Serializable

data class NewsData(
    val articles: ArrayList<Article>?,
    val status: String?
)

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable {

    val dateTime : String?
        get() =AppUtils.convertDateTimeToSpecifiedDateTime(
            "$publishedAt", "yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy,hh:mm a")
}

data class Source(
    val id: String?,
)