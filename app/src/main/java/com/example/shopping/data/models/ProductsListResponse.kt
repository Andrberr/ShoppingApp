package com.example.shopping.data.models

import com.squareup.moshi.Json

data class ProductsListResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "created") val dateTime: String? = null
)
