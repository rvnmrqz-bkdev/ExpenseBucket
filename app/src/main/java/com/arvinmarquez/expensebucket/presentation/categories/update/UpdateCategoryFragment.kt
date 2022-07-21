package com.arvinmarquez.expensebucket.presentation.categories.update

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.databinding.FragmentUpdateCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateCategoryFragment : Fragment() {

    private val args by navArgs<UpdateCategoryFragmentArgs>()

    private lateinit var binder: FragmentUpdateCategoryBinding
    private val viewModel: UpdateCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            viewModel.delete()
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

        initViews()
        observeUpdateState()
        observeDeleteState()
    }

    private fun initViews() {
        viewModel.setOpenedCategory(args.category)

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
            viewModel.update()
        }
    }

    private fun observeUpdateState() {
        viewModel.savingState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Category Updated", Toast.LENGTH_SHORT).show()
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

    private fun observeDeleteState() {
        viewModel.deletionState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Category deleted", Toast.LENGTH_SHORT).show()
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