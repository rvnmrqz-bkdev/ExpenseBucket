package com.arvinmarquez.expensebucket.presentation.expenses.fragment_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.TransactionItemViewBinding
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory

class TransactionListAdapter(val adapterOnClick: (ExpenseWithCategory) -> Unit) :
    RecyclerView.Adapter<TransactionListAdapter.MViewHolder>() {

    companion object {
        private const val TAG = "TransactionListAdapter"
    }

    private var listItems = emptyList<ExpenseWithCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item_view, parent, false)
        val binder = TransactionItemViewBinding.bind(view)
        return MViewHolder(binder)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val currentItem = listItems[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { adapterOnClick(currentItem) }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setItems(items: List<ExpenseWithCategory>) {
        this.listItems = items
        notifyDataSetChanged()
    }

    class MViewHolder(binder: TransactionItemViewBinding) : RecyclerView.ViewHolder(binder.root) {
        private val descriptionTxt = binder.descriptionTxt
        private val categoryDescTxt = binder.categoryTxt
        private val amountTxt = binder.amountTxt

        fun bind(item: ExpenseWithCategory) {
            val operator = if(item.category?.isExpense == true) "-" else "+"

            descriptionTxt.text = item.expense.description
            amountTxt.text = "$operator ${item.expense.amount}"
            categoryDescTxt.text = item.category?.description
        }
    }
}