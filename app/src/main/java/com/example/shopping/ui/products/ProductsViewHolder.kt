package com.example.shopping.ui.products

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ProductLayoutBinding
import com.example.shopping.domain.models.ProductModel

class ProductsViewHolder(
    private val binding: ProductLayoutBinding,
    private val cross: (itemId: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductModel) {
        with(binding) {
            crossButton.setOnClickListener {
                if (!product.isCrossed) {
                    product.isCrossed = true
                    crossedLine.isVisible = true
                    cross(product.id)
                }
            }
            titleView.text = product.name
            amountView.text = product.amount.toString()
            crossedLine.isVisible = product.isCrossed
        }
    }
}