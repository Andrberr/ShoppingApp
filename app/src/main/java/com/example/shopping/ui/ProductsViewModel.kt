package com.example.shopping.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.Repository
import com.example.shopping.domain.models.ProductModel
import com.example.shopping.domain.models.ProductsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _productsListsLiveData = MutableLiveData<List<ProductsListModel>>()
    val productsListsLiveData: LiveData<List<ProductsListModel>> get() = _productsListsLiveData

    private val _productsLiveData = MutableLiveData<List<ProductModel>>()
    val productsLiveData: LiveData<List<ProductModel>> get() = _productsLiveData

    var updateLists = false
    var updateProductsList = false

    fun getProductsLists() {
        viewModelScope.launch {
            _productsListsLiveData.value = repository.getProductsLists()
        }
    }

    fun createProductsList(name: String) {
        viewModelScope.launch {
            repository.createProductsList(name)
        }
    }

    fun removeProductsList(listId: Int) {
        viewModelScope.launch {
            repository.removeProductsList(listId)
        }
    }

    fun getProductsList(listId: Int) {
        viewModelScope.launch {
            _productsLiveData.value = repository.getProductsList(listId)
        }
    }

    fun addProductToList(listId: Int, name: String, amount: Int) {
        viewModelScope.launch {
            repository.addProductToList(listId, name, amount)
        }
    }

    fun removeProductFromList(listId: Int, itemId: Int) {
        viewModelScope.launch {
            repository.removeProductFromList(listId, itemId)
        }
    }

    fun updateLists() {
        viewModelScope.launch {
            while (updateLists) {
                getProductsLists()
                delay(TIMEOUT)
            }
        }
    }

    fun updateProductsList(listId: Int) {
        viewModelScope.launch {
            while (updateProductsList) {
                getProductsList(listId)
                delay(TIMEOUT)
            }
        }
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}