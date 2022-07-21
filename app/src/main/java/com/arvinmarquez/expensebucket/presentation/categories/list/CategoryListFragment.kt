package com.arvinmarquez.expensebucket.presentation.categories.list

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.CategoryListState
import com.arvinmarquez.expensebucket.presentation.categories.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private lateinit var binder: FragmentCategoryListBinding
    private val viewModel: CategoryListViewModel by viewModels()

    private val listAdapter = CategoryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.rvCategories.adapter = listAdapter

        listAdapter.onItemClicked = {
            goToUpdateCategoryFragment(it)
        }

        binder.fabAdd.setOnClickListener {
            goToNewCategoryFragment()
        }

        viewModel.dataState.observe(viewLifecycleOwner) {
            displayState(it)
        }
    }

    private fun displayState(state: CategoryListState) {
        binder.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        listAdapter.setItems(state.list)

        if (!TextUtils.isEmpty(state.error)) Toast.makeText(
            requireContext(),
            state.error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun goToUpdateCategoryFragment(category: Category) {
        if (category.id != 1L) {
            val action =
                CategoryListFragmentDirections.actionCategoryListFragmentToCategoryEditFragment(
                    category
                )
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Category is not editable", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToNewCategoryFragment() {
        findNavController().navigate(R.id.action_categoryListFragment_to_newCategoryFragment)
    }

}