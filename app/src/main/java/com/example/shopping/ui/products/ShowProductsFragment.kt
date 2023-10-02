package com.example.shopping.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.databinding.FragmentShowProductsBinding
import com.example.shopping.ui.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowProductsFragment : Fragment() {

    private var _binding: FragmentShowProductsBinding? = null
    private val binding get() = _binding!!

    private val args: ShowProductsFragmentArgs by navArgs()

    private val viewModel by activityViewModels<ProductsViewModel>()

    private val productsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        handleBackPressed()
        _binding = FragmentShowProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        updateProductsList()
    }

    private fun initViews() {
        with(binding) {
            productsAdapter.cross = { itemId ->
                viewModel.removeProductFromList(args.listId, itemId)
            }

            productsRecycler.apply {
                adapter = productsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            titleView.text = args.listName

            addButton.setOnClickListener {
                navigateToAddProduct()
            }
        }
    }

    private fun initObservers() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            productsAdapter.setProducts(products)
        }
    }

    private fun updateProductsList() {
        viewModel.updateProductsList = true
        viewModel.updateProductsList(args.listId)
    }

    private fun navigateToAddProduct() {
        val action =
            ShowProductsFragmentDirections.actionShowProductsFragmentToAddProductFragment(
                args.listId,
                args.listName
            )
        findNavController().navigate(action)
    }

    private fun navigateBack() {
        val action = ShowProductsFragmentDirections.actionShowProductsFragmentToListsFragment()
        findNavController().navigate(action)
    }

    private fun handleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateBack()
                }
            })
    }

    override fun onDestroyView() {
        _binding = null
        viewModel.updateProductsList = false
        super.onDestroyView()
    }
}