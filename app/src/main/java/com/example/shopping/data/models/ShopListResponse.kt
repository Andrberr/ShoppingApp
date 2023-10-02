package com.example.shopping.data.models

import com.squareup.moshi.Json

data class ShopListResponse(
    @Json(name = "shop_list") val productsLists: List<ProductsListResponse>? = null,
)
