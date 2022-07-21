package com.arvinmarquez.expensebucket.presentation.categories.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.databinding.FragmentNewCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCategoryFragment : Fragment() {

    private lateinit var binder: FragmentNewCategoryBinding
    private val viewModel: AddCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentNewCategoryBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.rbExpense.isChecked = true

        observeSavingState()
        setupView()
    }

    private fun observeSavingState() {
        viewModel.savingState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Category saved", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message ?: "Unexpected error occurred",
                        Toast.LENGTH_SHORT
                    ).show()

                    setLoading(false)
                }
            }
        }
    }

    private fun setupView() {
        binder.edtDescription.setText(viewModel.getDescription())
        binder.rbExpense.isChecked = viewModel.isExpense()
        binder.rbIncome.isChecked = !viewModel.isExpense()

        binder.edtDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setDescription(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binder.rgCategoryType.setOnCheckedChangeListener { _, optionId ->
            run {
                when (optionId) {
                    R.id.rbExpense -> {
                        viewModel.setIsExpense(true)
                    }
                    R.id.rbIncome -> {
                        viewModel.setIsExpense(false)
                    }
                }
            }
        }

        binder.fabSave.setOnClickListener {
            viewModel.save()
        }
    }

    private fun setLoading(shown: Boolean) {
        setViewEnabled(!shown)
        binder.progressBar.visibility = if (shown) View.VISIBLE else View.GONE
    }

    private fun setViewEnabled(enabled: Boolean) {
        binder.fabSave.isEnabled = enabled
        binder.edtDescription.isEnabled = enabled
        binder.rgCategoryType.isEnabled = enabled
    }
}