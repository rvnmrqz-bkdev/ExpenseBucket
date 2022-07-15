package com.arvinmarquez.expensebucket.presentation.categories


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.CategoryItemViewBinding
import com.arvinmarquez.expensebucket.features.category.domain.Category

class CategoryListAdapter :
    RecyclerView.Adapter<CategoryListAdapter.MViewHolder>() {

    companion object {
        private const val TAG = "CategoryListAdapter"
    }

    private var listItems = emptyList<Category>()
    var onItemClicked: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)
        val binder = CategoryItemViewBinding.bind(view)
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

    fun setItems(items: List<Category>) {
        this.listItems = items
        notifyDataSetChanged()
    }

    class MViewHolder(binder: CategoryItemViewBinding) : RecyclerView.ViewHolder(binder.root) {
        private val tvDescription = binder.tvDescription
        private val tvSubDescription = binder.tvSubDescription

        fun bind(item: Category) {
            tvDescription.text = item.description
            if (item.isExpense) {
                tvSubDescription.text = "Expense"
                tvSubDescription.setTextColor(Color.parseColor("#E64A19"))
            } else {
                tvSubDescription.text = "Income"
                tvSubDescription.setTextColor(Color.parseColor("#009497"))
            }
        }
    }
}