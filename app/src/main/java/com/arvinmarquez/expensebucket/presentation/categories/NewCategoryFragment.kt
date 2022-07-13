package com.arvinmarquez.expensebucket.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryAddBinding
import com.arvinmarquez.expensebucket.domain.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCategoryFragment : Fragment() {

    private lateinit var binder: FragmentCategoryAddBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentCategoryAddBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.rbExpense.isChecked = true

        binder.fabSave.setOnClickListener {
            saveCategory()
        }
    }

    private fun saveCategory() {
        val description = binder.edtDescription.text.toString().trim()
        val isExpense = binder.rbExpense.isChecked

        val category = Category(0, description, isExpense,true)
        viewModel.add(category)

        Toast.makeText(requireContext(),"Category saved", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

}