package com.arvinmarquez.expensebucket.presentation.fragment_update


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.databinding.FragmentUpdateTransactionBinding
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTransactionFragment : Fragment() {
    private val args by navArgs<UpdateTransactionFragmentArgs>()

    private lateinit var binder: FragmentUpdateTransactionBinding
    private val viewModel: UpdateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentUpdateTransactionBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        displayExpense(args.item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            deleteItem(args.item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayExpense(openedExpense: ExpenseWithCategory) {
        //display
        binder.descriptionEdt.setText(openedExpense.expense.description)
        binder.amountEdt.setText(openedExpense.expense.amount.toString())

        viewModel.categoryOptions.observe(viewLifecycleOwner) { options ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options)
            binder.categorySpn.adapter = adapter
            binder.categorySpn.setSelection(adapter.getPosition(openedExpense.category))
            binder.categorySpn.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?, v: View?, position: Int, id: Long
                    ) {
                        viewModel.selectedCategoryId = options[position].id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
        }

        binder.updateBtn.setOnClickListener {
            val description = binder.descriptionEdt.text.toString()
            var amount = binder.amountEdt.text.toString().toDoubleOrNull()
            if (amount == null) amount = 0.0

            val expense = ExpenseEntity(
                openedExpense.expense.id,
                description,
                viewModel.selectedCategoryId,
                amount,
                openedExpense.expense.date
            )

            viewModel.update(expense)
            Toast.makeText(requireContext(), "Item is updated", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun deleteItem(openedExpense: ExpenseWithCategory){
        viewModel.deleteItem(openedExpense.expense)
        Toast.makeText(requireContext(), "Item is deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }
}
