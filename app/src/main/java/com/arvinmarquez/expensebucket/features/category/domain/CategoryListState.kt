package com.arvinmarquez.expensebucket.features.category.domain

data class CategoryListState(
    val isLoading: Boolean = false,
    val list: List<Category> = emptyList(),
    val error: String = ""
)
