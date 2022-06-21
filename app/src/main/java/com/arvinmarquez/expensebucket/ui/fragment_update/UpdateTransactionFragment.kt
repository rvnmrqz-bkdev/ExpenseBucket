package com.arvinmarquez.expensebucket.ui.fragment_update

import android.R
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.databinding.FragmentUpdateTransactionBinding
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory

class UpdateTransactionFragment : Fragment() {
    private val args by navArgs<UpdateTransactionFragmentArgs>()

    private lateinit var binder: FragmentUpdateTransactionBinding
    private lateinit var viewModel: UpdateViewModel


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
        viewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        displayExpense(args.item)
    }

    private fun displayExpense(openedExpense: ExpenseWithCategory) {
        //display
        binder.descriptionEdt.setText(openedExpense.expense.description)
        binder.amountEdt.setText(openedExpense.expense.amount.toString())

        viewModel.categoryOptions.observe(viewLifecycleOwner) { options ->
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, options)
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

            viewModel.updateTransaction(expense)
            Toast.makeText(requireContext(), "Item is updated", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }


}
