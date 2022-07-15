package com.arvinmarquez.expensebucket.presentation.cash_flow


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import com.arvinmarquez.expensebucket.features.cashflow.domain.repository.CashFlowRepository
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashFlowViewModel
@Inject constructor(
    private val cashFlowRepositoryImpl: CashFlowRepository,
    private val categoryRepositoryImpl: CategoryRepository,
) : ViewModel() {

    private val _cashFlowList = MutableLiveData<List<CashFlow>>()
    val cashFlowList = _cashFlowList as LiveData<List<CashFlow>>

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList = _categoryList as LiveData<List<Category>>

    init {
        getCashFlowList()
        getCategoryList()
    }

    private fun getCashFlowList() {
        viewModelScope.launch(Dispatchers.IO) {
            cashFlowRepositoryImpl.getLiveCashFlow().collectLatest {
                _cashFlowList.postValue(it)
            }
        }
    }

    private fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepositoryImpl.getLiveCategories().collectLatest {
                _categoryList.postValue(it)
            }
        }
    }

    fun add(cashFlow: CashFlow) {
        viewModelScope.launch(Dispatchers.IO) {
            cashFlowRepositoryImpl.insert(cashFlow)
        }
    }

    fun update(cashFlow: CashFlow) {
        viewModelScope.launch(Dispatchers.IO) {
            cashFlowRepositoryImpl.update(cashFlow)
        }
    }

    fun delete(cashFlow: CashFlow) {
        viewModelScope.launch(Dispatchers.IO) {
            cashFlowRepositoryImpl.delete(cashFlow)
        }
    }
}