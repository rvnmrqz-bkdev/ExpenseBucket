package com.arvinmarquez.expensebucket.presentation.categories.fragment_add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryAddBinding
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.presentation.categories.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryAddFragment : Fragment() {

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
            val newCategory = CategoryEntity(
                0,
                binder.descriptionEdt.text.toString().trim(),
                binder.rbExpense.isChecked,
                true
            )
            val error = viewModel.add(newCategory)
            if (error == null) {
                Toast.makeText(requireContext(), "New category saved", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
}