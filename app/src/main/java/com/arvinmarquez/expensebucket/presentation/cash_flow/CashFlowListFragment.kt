package com.arvinmarquez.expensebucket.presentation.cash_flow

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentCashflowListBinding
import com.arvinmarquez.expensebucket.domain.CashFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashFlowListFragment : Fragment() {

    private lateinit var binder: FragmentCashflowListBinding
    private val viewModel: CashFlowViewModel by viewModels()
    private val listAdapter = CashFlowListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentCashflowListBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.recyclerView.adapter = listAdapter

        binder.addTransactionFab.setOnClickListener {
            goToNewCashFlowFragment()
        }

        listAdapter.onItemClicked = {
            goToUpdateCashFlowFragment(it)
        }

        viewModel.cashFlowList.observe(viewLifecycleOwner) { list ->
            binder.tvListMessage.visibility = if (list.isNotEmpty()) View.GONE else View.VISIBLE
            listAdapter.setItems(list)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_categories) goToCategoryManagementFragment()
        return super.onOptionsItemSelected(item)
    }

    private fun goToNewCashFlowFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_newCashFlowFragment)
    }

    private fun goToUpdateCashFlowFragment(cashFlow: CashFlow) {
        val action = CashFlowListFragmentDirections.actionHomeFragmentToUpdateCashFlowFragment(cashFlow)
        findNavController().navigate(action)
    }

    private fun goToCategoryManagementFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_categoryListFragment)
    }

}