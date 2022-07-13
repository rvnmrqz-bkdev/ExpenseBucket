package com.arvinmarquez.expensebucket.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.domain.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private lateinit var binder: FragmentCategoryListBinding
    private val viewModel: CategoryViewModel by viewModels()
    val listAdapter = CategoryListAdapter()

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

        viewModel.categoryList.observe(viewLifecycleOwner) { list ->
            listAdapter.setItems(list)
        }

        listAdapter.onItemClicked = {
            goToUpdateCategoryFragment(it)
        }

        binder.fabAdd.setOnClickListener {
            goToNewCategoryFragment()
        }
    }

    private fun goToUpdateCategoryFragment(category: Category) {
        if(category.id != 1L ){
            val action =
                CategoryListFragmentDirections.actionCategoryListFragmentToCategoryEditFragment(category)
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Category is not editable",Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToNewCategoryFragment() {
        findNavController().navigate(R.id.action_categoryListFragment_to_newCategoryFragment)
    }
}