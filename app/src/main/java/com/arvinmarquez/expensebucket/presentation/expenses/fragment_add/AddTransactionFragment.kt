package com.arvinmarquez.expensebucket.presentation.expenses.fragment_add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.databinding.FragmentAddTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private lateinit var binder: FragmentAddTransactionBinding
    private val viewModel: AddViewModel by viewModels()


    companion object {
        private const val TAG = "AddTransactionFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCategories.observe(viewLifecycleOwner) { list ->
            val categories: List<String> = list.map { it.description }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categories)
            binder.categorySpn.adapter = adapter
            binder.categorySpn.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        v: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.categoryId = list[position].id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
        }

        binder.descriptionEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.description = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binder.amountEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.amount = p0.toString().toDoubleOrNull()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binder.doneBtn.setOnClickListener {
            val errorMessage = viewModel.saveTransaction()
            if (TextUtils.isEmpty(errorMessage)) {
                Toast.makeText(requireContext(), "New item saved", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}