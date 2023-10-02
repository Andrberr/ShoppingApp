package com.example.shopping.data.mappers

import com.example.shopping.data.models.ProductsListResponse
import com.example.shopping.domain.models.ProductsListModel
import javax.inject.Inject

class ProductsListMapper @Inject constructor() {
    operator fun invoke(unmapped: ProductsListResponse): ProductsListModel =
        with(unmapped) {
            ProductsListModel(
                id = id ?: 0,
                name = name ?: "",
                dateTime = dateTime ?: "",
            )
        }
}