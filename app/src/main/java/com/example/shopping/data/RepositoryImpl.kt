package com.example.shopping.data

import com.example.shopping.data.mappers.ProductMapper
import com.example.shopping.data.mappers.ProductsListMapper
import com.example.shopping.data.network.ProductsService
import com.example.shopping.data.sources.TokenSource
import com.example.shopping.domain.Repository
import com.example.shopping.domain.models.ProductModel
import com.example.shopping.domain.models.ProductsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: ProductsService,
    private val productMapper: ProductMapper,
    private val productsListMapper: ProductsListMapper,
    tokenSource: TokenSource
) : Repository {
    private var apiKey = tokenSource.getToken()

    override suspend fun getProductsLists(): List<ProductsListModel> {
        return withContext(Dispatchers.IO) {
            val result = service.getProductsLists(apiKey)
            result.productsLists?.map { productsListMapper(it) } ?: emptyList()
        }
    }

    override suspend fun createProductsList(name: String) = runBlocking(Dispatchers.IO) {
        service.createProductsList(apiKey, name)
    }

    override suspend fun removeProductsList(listId: Int) = withContext(Dispatchers.IO) {
        service.removeProductsList(apiKey, listId)
    }

    override suspend fun getProductsList(listId: Int): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val result = service.getProductsList(apiKey, listId)
            result.products.map { productMapper(it) }
        }
    }

    override suspend fun addProductToList(listId: Int, name: String, amount: Int) =
        runBlocking(Dispatchers.IO) {
            service.addProductToList(apiKey, listId, name, amount)
        }

    override suspend fun removeProductFromList(listId: Int, itemId: Int) =
        withContext(Dispatchers.IO) {
            service.removeProductFromList(apiKey, listId, itemId)
        }
}