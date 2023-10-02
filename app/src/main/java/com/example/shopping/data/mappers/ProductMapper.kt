package com.example.shopping.data.mappers

import com.example.shopping.data.models.ProductResponse
import com.example.shopping.domain.models.ProductModel
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    operator fun invoke(unmapped: ProductResponse): ProductModel =
        with(unmapped) {
            ProductModel(
                id = id ?: 0,
                name = name ?: "",
                amount = amount ?: 0,
                isCrossed = isCrossed ?: false
            )
        }
}