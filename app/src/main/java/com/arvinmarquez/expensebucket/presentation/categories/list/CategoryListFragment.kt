package com.arvinmarquez.expensebucket.presentation.categories.list

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.features.category.domain.Category
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

        initViews()
        observeDataState()
    }

    private fun initViews() {
        binder.rvCategories.adapter = listAdapter

        listAdapter.onItemClicked = {
            goToUpdateCategoryFragment(it)
        }

        binder.fabAdd.setOnClickListener {
            goToNewCategoryFragment()
        }
    }

    private fun observeDataState() {
        viewModel.dataState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Success -> {
                    setLoading(false)
                    result.data?.let { listAdapter.setItems(it) }
                }
                is Resource.Error -> {
                    setLoading(false)
                    if (!TextUtils.isEmpty(result.message)) Toast.makeText(
                        requireContext(),
                        result.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
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

    private fun setLoading(isLoading: Boolean) {
        binder.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binder.rvCategories.isEnabled = !isLoading
    }

}