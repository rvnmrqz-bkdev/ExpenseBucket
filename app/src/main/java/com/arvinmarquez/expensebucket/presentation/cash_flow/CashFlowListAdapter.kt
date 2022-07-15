package com.arvinmarquez.expensebucket.presentation.cash_flow


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.TransactionItemViewBinding
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import com.arvinmarquez.expensebucket.core.utils.NumberUtils

class CashFlowListAdapter :
    RecyclerView.Adapter<CashFlowListAdapter.MViewHolder>() {

    companion object {
        private const val TAG = "CashFlowListAdapter"
    }

    private var listItems = emptyList<CashFlow>()
    var onItemClicked: ((CashFlow) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item_view, parent, false)
        val binder = TransactionItemViewBinding.bind(view)
        return MViewHolder(binder)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val currentItem = listItems[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClicked?.invoke(currentItem) }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setItems(items: List<CashFlow>) {
        this.listItems = items
        notifyDataSetChanged()
    }

    class MViewHolder(binder: TransactionItemViewBinding) : RecyclerView.ViewHolder(binder.root) {
        private val descriptionTxt = binder.descriptionTxt
        private val categoryDescTxt = binder.categoryTxt
        private val amountTxt = binder.amountTxt

        fun bind(item: CashFlow) {
            val operator = if (item.category?.isExpense == true) "-" else "+"
            val amount = NumberUtils().toMoneyFormat("P", item.amount)

            descriptionTxt.text = item.description
            amountTxt.text = "$operator $amount"
            categoryDescTxt.text = item.category?.description
        }
    }
}