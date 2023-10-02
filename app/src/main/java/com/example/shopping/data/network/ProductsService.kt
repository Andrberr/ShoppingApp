package com.example.shopping.data.network

import com.example.shopping.data.models.ItemListResponse
import com.example.shopping.data.models.ShopListResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsService {

    @POST("CreateTestKey")
    suspend fun getKey(): String?

    @POST("GetAllMyShopLists")
    suspend fun getProductsLists(
        @Query("key") key: String
    ): ShopListResponse

    @POST("CreateShoppingList")
    suspend fun createProductsList(
        @Query("key") key: String,
        @Query("name") name: String
    )

    @POST("RemoveShoppingList")
    suspend fun removeProductsList(
        @Query("key") key: String,
        @Query("list_id") listId: Int
    )

    @POST("GetShoppingList")
    suspend fun getProductsList(
        @Query("key") key: String,
        @Query("list_id") listId: Int
    ): ItemListResponse

    @POST("AddToShoppingList")
    suspend fun addProductToList(
        @Query("key") key: String,
        @Query("id") listId: Int,
        @Query("value") name: String,
        @Query("n") amount: Int,
    )

    @POST("RemoveFromList")
    suspend fun removeProductFromList(
        @Query("key") key: String,
        @Query("list_id") listId: Int,
        @Query("item_id") itemId: Int,
    )
}