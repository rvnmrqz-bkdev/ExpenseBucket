package com.arvinmarquez.expensebucket.presentation.expenses.fragment_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.FragmentHomeBinding
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        val TAG = "HomeFragment"
    }

    private lateinit var binder: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentHomeBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionListAdapter { item -> onItemClicked(item) }
        val recyclerView = binder.recyclerView
        recyclerView.adapter = adapter

        viewModel.liveList.observe(viewLifecycleOwner) { list ->
            adapter.setItems(list)
        }

        binder.addTransactionFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTransactionFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_categories) findNavController().navigate(R.id.action_homeFragment_to_categoryListFragment)
        return super.onOptionsItemSelected(item)
    }

    private fun onItemClicked(item: ExpenseWithCategory) {
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateTransactionFragment(item)
        findNavController().navigate(action)
    }

}