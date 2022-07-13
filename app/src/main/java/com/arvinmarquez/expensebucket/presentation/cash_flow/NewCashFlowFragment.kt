package com.arvinmarquez.expensebucket.presentation.cash_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentNewCashFlowBinding
import com.arvinmarquez.expensebucket.domain.CashFlow
import com.arvinmarquez.expensebucket.domain.Category
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NewCashFlowFragment : Fragment() {

    private lateinit var binder: FragmentNewCashFlowBinding
    private val viewModel: CashFlowViewModel by viewModels()

    private lateinit var spinnerAdapter: ArrayAdapter<Category>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentNewCashFlowBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categoryList.observe(viewLifecycleOwner) {
            spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it)
            binder.spnCategory.adapter = spinnerAdapter
        }

        binder.btnDone.setOnClickListener {
            saveCashFlow()
        }
    }

    private fun saveCashFlow() {
        val description = binder.edtDescription.text.toString().trim()
        val amount = binder.edtAmount.text.toString().trim().toDoubleOrNull() ?: 0.0
        val category = binder.spnCategory.selectedItem as Category

        val newCashFlow = CashFlow(0, description, category, amount, Calendar.getInstance().time)
        viewModel.add(newCashFlow)

        Toast.makeText(requireContext(), "Item saved", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }
}