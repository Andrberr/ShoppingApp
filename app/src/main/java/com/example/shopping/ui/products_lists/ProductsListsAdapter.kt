package com.example.shopping.ui.products_lists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ListLayoutBinding
import com.example.shopping.domain.models.ProductModel
import com.example.shopping.domain.models.ProductsListModel

class ProductsListsAdapter : RecyclerView.Adapter<ProductsListsViewHolder>() {

    private val productsLists = mutableListOf<ProductsListModel>()
    var itemClick: (listId: Int, listName: String) -> Unit = { _, _ -> }
    var cross: (listId: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListsViewHolder {
        val binding = ListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsListsViewHolder(binding, itemClick, cross)
    }

    override fun getItemCount(): Int = productsLists.size

    override fun onBindViewHolder(holder: ProductsListsViewHolder, position: Int) {
        holder.bind(productsLists[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductsLists(productsListModels: List<ProductsListModel>) {
        this.productsLists.clear()
        this.productsLists.addAll(productsListModels)
        notifyDataSetChanged()
    }
}