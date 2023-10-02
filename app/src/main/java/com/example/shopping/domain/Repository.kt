package com.example.shopping.domain

import com.example.shopping.domain.models.ProductModel
import com.example.shopping.domain.models.ProductsListModel

interface Repository {
    suspend fun getProductsLists(): List<ProductsListModel>
    suspend fun createProductsList(name: String)
    suspend fun removeProductsList(listId: Int)
    suspend fun getProductsList(listId: Int): List<ProductModel>
    suspend fun addProductToList(listId: Int, name: String, amount: Int)
    suspend fun removeProductFromList(listId: Int, itemId: Int)
}