package com.example.shopping.ui.add_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shopping.databinding.FragmentAddProductBinding
import com.example.shopping.ui.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val args: AddProductFragmentArgs by navArgs()

    private val viewModel by activityViewModels<ProductsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        handleBackPressed()
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addButton.setOnClickListener {
            if (addProduct()) {
                navigateBack()
            }
        }
    }

    private fun addProduct(): Boolean {
        val name = binding.titleInput.text.toString()
        if (name.isEmpty()) {
            showToast("Name is empty!")
        } else {
            val amount = binding.amountInput.text.toString()
            if (amount.isEmpty()) {
                showToast("Amount is empty!")
            } else {
                viewModel.addProductToList(args.listId, name, amount.toInt())
                return true
            }
        }
        return false
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun navigateBack() {
        val action =
            AddProductFragmentDirections.actionAddProductFragmentToShowProductsFragment(
                args.listId,
                args.listName
            )
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
        super.onDestroyView()
    }
}