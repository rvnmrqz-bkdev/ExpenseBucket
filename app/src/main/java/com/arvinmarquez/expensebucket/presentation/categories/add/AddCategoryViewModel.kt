package com.arvinmarquez.expensebucket.presentation.categories.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.use_case.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase
) : ViewModel() {

    private var _category = Category(0, "", isExpense = true, isActive = true)
    private var _state = MutableLiveData<Resource<Category>>()
    var savingState = _state as LiveData<Resource<Category>>

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            addCategoryUseCase.add(_category).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.postValue(Resource.Loading())
                    }
                    is Resource.Success -> {
                        result.data?.let { it -> _state.postValue(Resource.Success(it)) }
                    }
                    is Resource.Error -> {
                        _state.postValue(
                            Resource.Error(
                                result.message ?: "Unexpected error occurred"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getDescription(): String = _category.description

    fun isExpense(): Boolean = _category.isExpense

    fun setDescription(description: String) {
        _category.description = description
    }

    fun setIsExpense(isExpense: Boolean) {
        _category.isExpense = isExpense
    }

}