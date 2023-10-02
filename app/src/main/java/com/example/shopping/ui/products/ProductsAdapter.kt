package com.example.shopping.ui.products

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ProductLayoutBinding
import com.example.shopping.domain.models.ProductModel

class ProductsAdapter : RecyclerView.Adapter<ProductsViewHolder>() {

    private val products = mutableListOf<ProductModel>()
    var cross: (itemId: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding =
            ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding, cross)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<ProductModel>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}