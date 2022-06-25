package com.arvinmarquez.expensebucket.presentation.categories.fragment_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.presentation.categories.CategoryViewModel
import com.arvinmarquez.expensebucket.presentation.expenses.fragment_list.HomeFragmentDirections
import com.arvinmarquez.expensebucket.presentation.expenses.fragment_list.TransactionListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private lateinit var binder: FragmentCategoryListBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryListAdapter { item -> onItemClicked(item) }
        val recyclerView = binder.rvCategories
        recyclerView.adapter = adapter

        viewModel.categoryList.observe(viewLifecycleOwner) { list ->
            adapter.setItems(list)
        }

        binder.fabAdd.setOnClickListener{
            findNavController().navigate(R.id.action_categoryListFragment_to_categoryAddFragment)
        }
    }

    private fun onItemClicked(item: CategoryEntity) {
        val action = CategoryListFragmentDirections.actionCategoryListFragmentToCategoryEditFragment(item)
        findNavController().navigate(action)
    }

}