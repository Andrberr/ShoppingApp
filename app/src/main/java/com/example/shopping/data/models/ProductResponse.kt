package com.example.shopping.data.models

import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "created") val amount: Int? = null,
    @Json(name = "is_crossed") val isCrossed: Boolean? = null,
)
