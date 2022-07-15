package com.arvinmarquez.expensebucket.presentation.categories

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentUpdateCategoryBinding
import com.arvinmarquez.expensebucket.features.category.domain.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateCategoryFragment : Fragment() {

    private val args by navArgs<UpdateCategoryFragmentArgs>()

    private lateinit var binder: FragmentUpdateCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            deleteCategory()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentUpdateCategoryBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.edtDescription.setText(args.category.description)

        if (args.category.isExpense) binder.rbExpense.isChecked = true
        else binder.rbIncome.isChecked = true

        binder.fabSave.setOnClickListener {
            saveCategory()
        }
    }

    private fun saveCategory() {
        val description = binder.edtDescription.text.toString().trim()
        val isExpense = binder.rbExpense.isChecked

        val category = Category(args.category.id, description, isExpense, args.category.isActive)
        viewModel.update(category)

        Toast.makeText(requireContext(),"Category saved", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun deleteCategory() {
        viewModel.delete(args.category)

        Toast.makeText(requireContext(),"Category deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

}