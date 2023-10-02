package com.example.shopping.ui.products_lists

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ListLayoutBinding
import com.example.shopping.domain.models.ProductsListModel

class ProductsListsViewHolder(
    private val binding: ListLayoutBinding,
    private val itemClick: (listId: Int, listName: String) -> Unit,
    private val cross: (listId: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productsListModel: ProductsListModel) {
        itemView.setOnClickListener {
            itemClick(productsListModel.id, productsListModel.name)
        }
        with(binding) {
            crossedLine.isVisible = false
            crossButton.isVisible = true
            titleView.text = productsListModel.name
            dateView.text = productsListModel.dateTime
            crossButton.setOnClickListener {
                if (!crossedLine.isVisible) {
                    crossedLine.isVisible = true
                    crossButton.isVisible = false
                    cross(productsListModel.id)
                }
            }
        }
    }
}