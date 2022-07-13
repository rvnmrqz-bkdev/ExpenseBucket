package com.arvinmarquez.expensebucket.presentation.cash_flow

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentUpdateCashFlowBinding
import com.arvinmarquez.expensebucket.domain.CashFlow
import com.arvinmarquez.expensebucket.domain.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateCashFlowFragment : Fragment() {

    private val args by navArgs<UpdateCashFlowFragmentArgs>()

    private lateinit var binder: FragmentUpdateCashFlowBinding
    private val viewModel: CashFlowViewModel by viewModels()
    private lateinit var spinnerAdapter: ArrayAdapter<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentUpdateCashFlowBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) deleteCashFlow()
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.edtDescription.setText(args.cashFlow.description)
        binder.edtAmount.setText(args.cashFlow.amount.toString())

        viewModel.categoryList.observe(viewLifecycleOwner) {
            spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it)
            binder.spnCategory.adapter = spinnerAdapter
            binder.spnCategory.setSelection(spinnerAdapter.getPosition(args.cashFlow.category))
        }

        binder.btnDone.setOnClickListener {
            updateCashFlow()
        }
    }

    private fun updateCashFlow() {
        val description = binder.edtDescription.text.toString().trim()
        val amount = binder.edtAmount.text.toString().trim().toDoubleOrNull()
        val category = binder.spnCategory.selectedItem as Category

        val updatedCashFlow = CashFlow(
            args.cashFlow.id,
            description,
            category,
            amount ?: 0.0,
            args.cashFlow.date
        )
        viewModel.update(updatedCashFlow)

        Toast.makeText(requireContext(),"Item saved", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun deleteCashFlow() {
        viewModel.delete(args.cashFlow)

        Toast.makeText(requireContext(),"Item deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

}