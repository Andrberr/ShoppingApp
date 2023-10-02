package com.example.shopping.domain.models

data class ProductModel(
    val id: Int,
    val name: String,
    val amount: Int,
    var isCrossed: Boolean
)
