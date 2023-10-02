package com.example.shopping.data.models

import com.squareup.moshi.Json

data class ItemListResponse(
    @Json(name = "item_list") val products: List<ProductResponse>
)