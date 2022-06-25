package com.arvinmarquez.expensebucket.presentation.categories.fragment_list


import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.databinding.CategoryItemViewBinding

class CategoryListAdapter( val adapterOnClick: (CategoryEntity) -> Unit) :
    RecyclerView.Adapter<CategoryListAdapter.MViewHolder>() {

    companion object {
        private const val TAG = "CategoryListAdapter"
    }

    private var listItems = emptyList<CategoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)
        val binder = CategoryItemViewBinding.bind(view)
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

    fun setItems(items: List<CategoryEntity>) {
        this.listItems = items
        notifyDataSetChanged()
    }

    class MViewHolder(binder: CategoryItemViewBinding) : RecyclerView.ViewHolder(binder.root) {
        private val descriptionTxt = binder.descriptionTxt
        private val operatorTxt = binder.operatorTxt

        fun bind(item: CategoryEntity) {
            descriptionTxt.text = item.description
            operatorTxt.text = if (item.isExpense)  "Expense" else "Income"
        }
    }
}