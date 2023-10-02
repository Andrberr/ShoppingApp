package com.example.shopping.ui.products_lists

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.databinding.FragmentListsBinding
import com.example.shopping.databinding.InputNameDialogLayoutBinding
import com.example.shopping.ui.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListsFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ProductsViewModel>()

    private val productsListsAdapter = ProductsListsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        handleBackPressed()
        _binding = FragmentListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        updateList()
    }

    private fun initViews() {
        with(binding) {
            productsListsAdapter.itemClick = { listId, listName ->
                navigateToProducts(listId, listName)
            }

            productsListsAdapter.cross = { listId ->
                viewModel.removeProductsList(listId)
            }

            productsListsRecycler.apply {
                adapter = productsListsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            addButton.setOnClickListener {
                showInputNameDialog()
            }
        }
    }

    private fun initObservers() {
        viewModel.productsListsLiveData.observe(viewLifecycleOwner) { list ->
            productsListsAdapter.setProductsLists(list)
        }
    }

    private fun updateList() {
        viewModel.updateLists = true
        viewModel.updateLists()
    }

    private fun navigateToProducts(listId: Int, listName: String) {
        val action =
            ListsFragmentDirections.actionListsFragmentToShowProductsFragment(listId, listName)
        findNavController().navigate(action)
    }

    private fun showInputNameDialog() {
        val builder = AlertDialog.Builder(requireContext())

        val dialogLayout = InputNameDialogLayoutBinding.inflate(layoutInflater, null, false)
        builder.setView(dialogLayout.root)
        val alertDialog = builder.create()

        alertDialog.window?.apply {
            val layoutParams = attributes
            layoutParams.gravity = Gravity.CENTER
            attributes = layoutParams
        }

        dialogLayout.addButton.setOnClickListener {
            val name = dialogLayout.titleInput.text.toString()
            if (name.isNotEmpty()) {
                viewModel.createProductsList(name)
                viewModel.getProductsLists()
            }
        }
        alertDialog.show()
    }

    private fun handleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            })
    }

    override fun onDestroyView() {
        _binding = null
        viewModel.updateLists = false
        super.onDestroyView()
    }
}