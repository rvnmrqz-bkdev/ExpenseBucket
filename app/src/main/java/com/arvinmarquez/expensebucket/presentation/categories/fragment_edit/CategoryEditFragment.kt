package com.arvinmarquez.expensebucket.presentation.categories.fragment_edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryEditBinding
import com.arvinmarquez.expensebucket.databinding.FragmentCategoryListBinding
import com.arvinmarquez.expensebucket.presentation.categories.CategoryViewModel
import com.arvinmarquez.expensebucket.presentation.expenses.fragment_update.UpdateTransactionFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryEditFragment : Fragment() {

    private val args by navArgs<CategoryEditFragmentArgs>()
    private lateinit var binder: FragmentCategoryEditBinding
    private val viewModel: CategoryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            viewModel.delete(args.category)
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentCategoryEditBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayItem(args.category)
    }

    private fun displayItem(category: CategoryEntity) {
        binder.descriptionEdt.setText(category.description)
        if (category.isExpense) binder.rbExpense.isChecked = true
        else binder.rbIncome.isChecked = true

        binder.fabSave.setOnClickListener {
            val updatedCategory = CategoryEntity(
                category.id,
                binder.descriptionEdt.text.toString(),
                binder.rbExpense.isChecked,
                true
            )

            val error = viewModel.update(updatedCategory)
            if (error == null) {
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }


}